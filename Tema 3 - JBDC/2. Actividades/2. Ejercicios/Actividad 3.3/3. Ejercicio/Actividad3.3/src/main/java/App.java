import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class App {

    private static final File FILE_DATABASE = new File("./database");
    private static final String DB_FOLDER = "./database/schooldb";
    private static final String FILE_DEPARTMENTS = "./files/departments.txt";
    private static final String FILE_TEACHERS = "./files/teachers.txt";
    private static final String QUERY_CREATE_TABLE_DEPARTAMENTOS = "CREATE TABLE departamentos (Dept_num INT NOT NULL PRIMARY KEY,Name VARCHAR(20),Office VARCHAR(20));";
    private static final String QUERY_CREATE_TABLE_PROFESORES = "CREATE TABLE profesores (id INT NOT NULL PRIMARY KEY,Name VARCHAR(15),Surname VARCHAR(40),Email VARCHAR(50),Start_date DATE,Dept_num INT,FOREIGN KEY (Dept_num) REFERENCES departamentos(Dept_num));";
    private static final String QUERY_SELECT_DEPARTAMENTOS = "SELECT * FROM departamentos";
    private static final String QUERY_SELECT_PROFESORES = "SELECT * FROM profesores";

    public static void main(String[] args) {

        // 1. Borrar carpeta y base de datos
        eliminarDirectorio();

        // 2. Crear carpeta y base de datos
        crearCarpetaDatabase();
        System.out.println("Creando base de datos y conectando");

        // 3. Crear tabla departamentos
        crearTabla(QUERY_CREATE_TABLE_DEPARTAMENTOS, "departamentos", FILE_DEPARTMENTS);

        // 4. Mostrar tabla departamentos
        mostrarTabla("departamentos", QUERY_SELECT_DEPARTAMENTOS);

        // 5. Crear y mostrar tabla profesores
        crearTabla(QUERY_CREATE_TABLE_PROFESORES, "profesores", FILE_TEACHERS);
        mostrarTabla("profesores", QUERY_SELECT_PROFESORES);

    }

    //  1. Eliminar todos los ficheros y carpeta de database
    public static void eliminarDirectorio() {
        if (FILE_DATABASE.exists()) {
            for (File f : FILE_DATABASE.listFiles()) {
                f.delete();
            }
            FILE_DATABASE.delete();
            System.out.println("Borrando " + FILE_DATABASE.getName());
        }
    }

    // Crear carpeta database
    public static void crearCarpetaDatabase() {
        if (!FILE_DATABASE.exists()) {
            FILE_DATABASE.mkdir();
        } else System.out.println("Ya existia");
    }

    // Crear la tabla departamentos
    public static Connection establecerConexion() {
        try {
            return DriverManager.getConnection("jdbc:hsqldb:" + DB_FOLDER);
        } catch (SQLException e) {
            System.out.println("NO se ha podido establecer la conexión");
            return null;
        }
    }

    // 3.1 Crear tabla
    // 3.2 Comprobar antes si la tabla estaba creada ya
    // 3.3 Vaciar la tabla
    public static Boolean comprobarSiExisteTabla(String nombreTabla) {
        try (Connection con = establecerConexion()) {
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet rs = databaseMetaData.getTables(null, null, nombreTabla.toUpperCase(), null);
            // Si existe la tabla rs.next() sera true
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Comprobar si existe: NO se ha podido establecer la conexión");
            return null;
        }
    }

    public static void vaciarContenidoTabla(String nombreTabla) {
        try (Connection con = establecerConexion()) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DROP DATABASE " + nombreTabla + ";");
        } catch (SQLException e) {
            System.out.println("Vaciar contenido: NO se ha podido establecer la conexión");
        }
    }

    public static void crearTabla(String query, String nombreTabla, String nombreArchivo) {
        if (!comprobarSiExisteTabla(nombreTabla)) {
            try (Connection con = establecerConexion()) {
                Statement statement = con.createStatement();
                statement.executeUpdate(query);
                rellenarTabla(nombreArchivo, nombreTabla);
                statement.close();
            } catch (SQLException e) {
                System.out.println("Crear tabla: NO se ha podido establecer la conexión");
            }
        }
    }

    // 4. Rellenar tabla
    public static void rellenarTabla(String nombreArchivo, String nombreTabla) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
            Statement statement = establecerConexion().createStatement()){

            int filasModificadas = 0;
            String linea = br.readLine();

            while (linea != null) {
                String[] datos = linea.split(",");
                for (int i = 0; i < datos.length; i++) {
                    if (datos[i].isEmpty()) {
                        datos[i] = "NULL";
                    } else {
                        datos[i] = "'" + datos[i] + "'";
                    }
                }
                String query = "INSERT INTO " + nombreTabla + " VALUES " + "(" + String.join(", ", datos) + ");";

                statement.executeUpdate(query);
                linea = br.readLine();
                filasModificadas++;
            }
            statement.close();
            System.out.println("Creando y rellenando la tabla " + nombreTabla + "...\n\t... " + filasModificadas + " filas modificadas");
        } catch (IOException e) {
            System.out.println("NO se puede leer el archivo");
        } catch (SQLException e) {
            System.out.println("Rellenar tabla: NO se puede establecer la conexión");
        }
    }

    public static void mostrarTabla(String nombreTabla, String query) {
        try (Connection connection = establecerConexion()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // TODO: quiero leer el numero de columnas
            // tiene 3, guardar 3 y guardar en cada variable el nombre de dicha columna
            /// saber el numero de columna

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet rsColumn = databaseMetaData.getColumns(null, null, nombreTabla.toUpperCase(), null);

            ArrayList<String> nombreColumna = new ArrayList<>();
            while (rsColumn.next()) {
                nombreColumna.add(rsColumn.getString(4));
            }
            System.out.println("SELECT * FROM " + nombreTabla);
            while (resultSet.next()) {
                System.out.print("--> ");

                for (String row : nombreColumna) {
                    String columna = row;
                    String valor = resultSet.getString(columna);
                    System.out.print(columna + ": " + valor + "; ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Mostrar tabla: NO se pudo establecer la conexión");
        }
    }
}
