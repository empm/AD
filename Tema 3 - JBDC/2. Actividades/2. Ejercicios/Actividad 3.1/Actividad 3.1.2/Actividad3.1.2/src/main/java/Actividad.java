import java.sql.*;
import java.util.ArrayList;

public class Actividad {
    public static void main(String[] args) {

        // 1. Instanciar la conexión
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

            ResultSet res = mt.getTables(null, null, null, new String[]{"TABLE"});

            ArrayList<String> tableName = new ArrayList<>();
            while (res.next()){
                tableName.add(res.getString("TABLE_NAME"));
            }
            System.out.println("Hay " + tableName.size() + " tablas\n");

            // 4. Obtener los nombres de la columna iterando
            for (int i = 0; i < tableName.size(); i++) {
                ResultSet rs = mt.getColumns(null, null, tableName.get(i), null);

                // 5. Mover el puntero sobre cada columna
                int j = 1;
                while (rs.next()) {
                    System.out.println((j++) + ". " + rs.getString("COLUMN_NAME")); // Indice 4: COLUMN_NAME
                }

                rs.close();
                System.out.println();
            }


            res.close();

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
