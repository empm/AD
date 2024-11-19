package org.vawacode;

import java.sql.*;

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
 *
 * CREATE TABLE Sports (
 * Cod INT NOT NULL PRIMARY KEY,
 * Name VARCHAR(20));
 *
 * CREATE TABLE Players (
 * cod INT NOT NULL PRIMARY KEY,
 * Name VARCHAR(50),
 * cod_sport INT,
 * FOREIGN KEY (cod_sport) REFERENCE Sport(Cod));
 */
public class Test {

    public static void main(String[] args) {

        String urlDB = "jdbc:sqlite:actividad1/src/main/resources/bas.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(urlDB);
            // if (connection != null) System.out.println("Conexion establecida");

            Statement st = connection.createStatement();

            String sql = "SELECT * FROM departamentos";

            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();



            while (rs.next()){
                int dept_num = rs.getInt("dept_num");
                String name = rs.getString("name");
                String office = rs.getString("office");

                System.out.println(rsmd.getColumnName(1) + ": " + dept_num +
                        " " + rsmd.getColumnName(2) + ": " + name +
                        " " + rsmd.getColumnName(3) + ": " + office);
            }

            st.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }
}

