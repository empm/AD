import java.sql.*;

public class Actividad {
    public static void main(String[] args) {

        // 1. Instanciar la conexión +Import
        Connection con = null;

        try {

            // 2. Configurar el driver para HSQLDB +Import
            con = DriverManager.getConnection("jdbc:hsqldb:Act2.2.HSQLDB");

            /* Incluir en la raíz del proyecto:
                - *.HSQLDB.log
                - *.HSQLDB.properties
                - *.HSQLDB.script
                - *.HSQLDB.temp
             */

            // 2.2 Comprobar que se establece la conexión (Opcional)
            if (con != null) System.out.println("Conexión establecida!\n");

            // 3. Obtener los metadatos de la DB +Import
            DatabaseMetaData mt = con.getMetaData();

            // 4. Obtener los datos sobre tablas (filtrando solo nombres de las tablas) +Import
            ResultSet rs = mt.getTables(null, null, null, new String[] {"TABLE"});

            // 5. Mover el punto sobre cada tabla
            int i = 1; // Numeramos cada tabla
            while (rs.next()) {
                System.out.println((i++) + ". " + rs.getString(3)); // Indice 3: TABLE_NAME
            }
            rs.close(); // Cerramos el cursor

        } catch (SQLException e) { // Import para tratar excepciones al establecer conexión
            e.printStackTrace();
        } finally { // Tratamos la excepción al cerrar la conexión
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
