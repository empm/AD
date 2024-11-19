package org.vawacode;

import java.sql.*;
import java.util.Scanner;

/**
 * Pag. 17
 * Escribe un programa que se conecte a una base de datos HSQLDB y use
 * statements para:
 * 1. 2. Crear (si no existe) una base de datos con tablas de Sports y Players
 * Hacer un menú con las siguientes opciones:
 * 2.1. Preguntar al usuario para añadir un deporte nuevo
 * 2.2. Preguntar al usuario para añadir un jugador nuevo
 * 2.3. Mostrar el nombre de cada jugador y el deporte que juega usando una query
 * 2.4. Preguntar al usuario el COD de un deporte para borrarlo a éste y a los jugadores asociados
 */

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String hsqldb = "jdbc:hsqldb:actividad3/src/main/resources/database.HSQLDB";
        int menu = 0;


        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            /* 1. CREAR SI NO EXISTE UNA BASE DE DATOS (SPORTS & PLAYERS) */
            connection = DriverManager.getConnection(hsqldb);
            // if (connection != null) System.out.println("Conexion establecida");

            st = connection.createStatement();

            // Creamos las tablas
            try {
                int update = st.executeUpdate(createDatabase());
                System.out.println("Creamos base de datos...");
                System.out.println();
            } catch (SQLException e) {
                System.out.println("Base de datos ya creada...");
                System.out.println();
            }

            /* 2. CREAR MENU */
            while (menu != 5) {

                System.out.println("\nSelecciona una opción\n-------------------------");
                System.out.println("1. Añadir un nuevo deporte" +
                        "\n2. Añadir un nuevo jugador" +
                        "\n3. Mostrar el nombre de cada jugador y el deporte al que juega" +
                        "\n4. Indica el código de un deporte para borrarlo (se borrarán también los jugadores asociados)" +
                        "\n5. Salir\n");

                menu = scan.nextInt();
                scan.nextLine();

                switch (menu) {
                    case 1:
                        /* Añadir un nuevo deporte */

                        // mostrar los deportes existentes
                        System.out.println("Deportes existentes:");
                        rs = st.executeQuery("SELECT * FROM Sports;");
                        while (rs.next()) {
                            int codSport = rs.getInt(1);
                            String sportName = rs.getString(2);
                            System.out.println(codSport + " " + sportName);
                        }

                        // Añadirlo a la db
                        System.out.println("\nEscribe el nombre del nuevo deporte: ");
                        String newSport = scan.nextLine();
                        try {
                            // Conseguir el id mas alto para hacerlo autoincrement
                            rs = st.executeQuery("SELECT MAX(Cod) AS max_cod FROM Sports");
                            int newCod = 1;
                            if (rs.next()) {
                                newCod = rs.getInt("max_cod") + 1;
                            }
                            rs.close();
                            String query = "INSERT INTO Sports VALUES (" + newCod + ", '" + newSport + "');";
                            st.executeUpdate(query);
                            System.out.println("Deporte añadido con éxito");

                        } catch (SQLException e) {
                            System.out.println("Algo ha pasado");
                            e.printStackTrace();
                        }
                        break;

                    case 2:
                        /* Añadir un nuevo jugador */

                        // Vista previa de los jugadores existentes
                        System.out.println("Jugadores existentes:");
                        rs = st.executeQuery("SELECT * FROM Players;");

                        while (rs.next()) {
                            String playerName = rs.getString(2);
                            System.out.println(playerName);
                        }

                        // Crear un nuevo jugador
                        System.out.println("\nEscribe el nombre del nuevo jugador: ");
                        String newPlayer = scan.nextLine();
                        rs = st.executeQuery("SELECT * FROM Sports;");

                        // Vista previa de los deportes creados
                        System.out.println("Escribe el id del deporte que practica " + newPlayer + ": ");
                        while (rs.next()) {
                            int codSport = rs.getInt(1);
                            String sportName = rs.getString(2);
                            System.out.println(codSport + " " + sportName);
                        }
                        int playerSport = scan.nextInt();

                        // Añadir al jugador a la db
                        try {
                            // Conseguir el codigo mas alto
                            rs = st.executeQuery("SELECT MAX(Cod) AS max_cod FROM Players");
                            int newCod = 1;
                            if (rs.next()) {
                                newCod = rs.getInt("max_cod") + 1;
                            }
                            rs.close();
                            // Insertar datos jugador
                            String query = "INSERT INTO Players VALUES (" + newCod + ", '" +
                                    newPlayer + "', " +
                                    "'" + playerSport + "');";
                            st.executeUpdate(query);
                            System.out.println("Jugador añadido con éxito");

                        } catch (SQLException e) {
                            System.out.println("No se ha podido añadir");
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        /* Mostrar nombre de cada jugador y su deporte */

                        rs = st.executeQuery("SELECT Players.Name, Sports.Name FROM Players, Sports " +
                                "WHERE Players.cod_sport = Sports.cod;");


                        // Mostrar el resultado
                        while (rs.next()) {
                            String playerName = rs.getString("Players.Name");
                            String sportName = rs.getString("Sports.name");
                            System.out.println("Jugador: " + playerName + ", Deporte: " + sportName);
                        }
                        break;


                    case 4:
                        /* Eliminar un deporte y al jugador asociado */

                        System.out.println("Introduce el codigo que deseas eliminar: ");

                        // Vista previa de los deportes existentes
                        System.out.println("Deportes existentes:");
                        rs = st.executeQuery("SELECT * FROM Sports;");
                        while (rs.next()) {
                            int cod = rs.getInt(1);
                            String sport = rs.getString(2);
                            System.out.println(cod + ": " + sport);
                        }

                        int codToDelete = scan.nextInt();
                        String querySport = "DELETE FROM Sports WHERE cod =" + codToDelete + ";";
                        String queryPlayer = "DELETE FROM Players WHERE cod_sport =" + codToDelete + ";";

                        // Ejecutar la query para eliminar

                        try {
                            rs = st.executeQuery(queryPlayer);
                            rs = st.executeQuery(querySport);

                            System.out.println("Deporte y jugador asociado eliminados");
                        } catch (SQLException e){
                            System.out.println("Algo fallo");
                            e.printStackTrace();
                        }
                        rs.close();
                        break;

                    case 5:
                        return;

                    default:
                        break;

                }
            }

            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String createDatabase() {
        /**
         *  * CREATE TABLE Sports (
         *  * Cod INT NOT NULL PRIMARY KEY,
         *  * Name VARCHAR(20));
         *  *
         *  * CREATE TABLE Players (
         *  * cod INT NOT NULL PRIMARY KEY,
         *  * Name VARCHAR(50),
         *  * cod_sport INT,
         *  * FOREIGN KEY (cod_sport) REFERENCE Sport(Cod));
         */

        return "CREATE TABLE Sports (Cod INT NOT NULL ON DELETE CASCADE PRIMARY KEY, Name VARCHAR(20)); " +
                "CREATE TABLE Players (Cod INT NOT NULL PRIMARY KEY,Name VARCHAR(50),Cod_sport INT, " +
                "FOREIGN KEY (cod_sport) REFERENCES Sports(Cod));";
    }
}