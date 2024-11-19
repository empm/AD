import java.sql.*;

public class BasicStatement {
    public static void main(String[] args) {
        Connection con = null;
        String subProtocolo = "jdbc:sqlite:./src/main/resources/db.db";

        try {
            // 1. Crear conexion al a db
            con = DriverManager.getConnection(subProtocolo);

            // 2. Crear tabla (si no existe)
            try {
                Statement st = con.createStatement();
                String query = "CREATE TABLE alumnos (id INT PRIMARY KEY," +
                        "name VARCHAR(90));";
                st.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("No se ha podido crear tabla o ya existía");
            }

            // 3. Insertar datos
            try {
                Statement st = con.createStatement();
                String query = "INSERT INTO alumnos VALUES" +
                        "(1, 'Enrique'), (2, 'Alicia'), (3, 'Elena'), (4, 'Maria');";
                st.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("No se ha podido insertar datos o ya existian");
            }

            // 4. Ver datos
            try {
                Statement st = con.createStatement();
                String query = "SELECT * FROM alumnos;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println("Numero de alumno: " + id + ". Alumno: " + name);
                }
            } catch (SQLException e) {
                System.out.println("No se ha podido mostrar ningun dato");
            }

            // 5. Eliminar datos
            try {
                Statement st = con.createStatement();
                String query = "DELETE FROM alumnos WHERE id = '4';";
                st.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println("No se ha podido eliminar o no existe el dato");
            }
            // 5.1 Visualizar los datos para comprobar que se han eliminado
            try {
                Statement st = con.createStatement();
                String query = "SELECT * FROM alumnos;";
                ResultSet rs = st.executeQuery(query);
                System.out.println("----------------");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println("Numero de alumno: " + id + ". Alumno: " + name);
                }
            } catch (SQLException e) {
                System.out.println("No se ha podido mostrar ningun dato");
            }

        } catch (SQLException e) {
            System.out.println("No se ha podido conectar a la db");
        }
    }
}
