import java.sql.*;

public class BasicPreparedStatement {
    public static void main(String[] args) {
        Connection con = null;
        String subProtocolo = "jdbc:sqlite:./src/main/resources/db.db";

        try {
            // 1. Crear conexion al a db
            con = DriverManager.getConnection(subProtocolo);

            // 4. Ver datos
            try {
                String query = "SELECT * FROM alumnos WHERE name = ? OR id = ?;";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, "Enrique");
                ps.setInt(2, 2);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println("Numero de alumno: " + id + ". Alumno: " + name);
                }
                ps.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("No se ha podido mostrar ningun dato");
            }

        } catch (SQLException e) {
            System.out.println("No se ha podido conectar a la db");
        }
    }
}
