import javax.xml.catalog.Catalog;
import java.sql.*;
import java.util.Scanner;

/**
 * Elegir la base de datos
 * SQLite
 * HSQLDB
 * Salir
 */


public class Actividad3_1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int menu = 0;
        String db;

        while (menu != 3) {
            System.out.println("Elige una base de datos: " +
                    "\n1. SQLite Database" +
                    "\n2. HSQLDB Databsae" +
                    "\n3. Salir\n");

            menu = scan.nextInt();

            switch (menu) {
                case 1:
                    db = "sqlite";
                    dataBaseInfo(dataBaseMetaData(driverConection(db)));
                    dataBaseTablesInfo(dataBaseMetaData(driverConection(db)));
                    break;
                case 2:
                    db = "hsqldb";
                    dataBaseInfo(dataBaseMetaData(driverConection(db)));
                    dataBaseTablesInfo(dataBaseMetaData(driverConection(db)));
                    break;
                case 3:
                    return;
                default:
                    System.out.println("def");
                    break;
            }
        }
    }

    public static Connection driverConection(String db) {
        if (db.equalsIgnoreCase("sqlite")) {
            db = "jdbc:sqlite:bas.db";
        } else if (db.equalsIgnoreCase("hsqldb")) {
            db = "jdbc:hsqldb:Act2.2.HSQLDB";
        }
        Connection conection = null;
        try {
            conection = DriverManager.getConnection(db);
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            return conection;
        }
    }

    public static DatabaseMetaData dataBaseMetaData(Connection connection) {
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return metaData;
        }
    }

    public static void dataBaseInfo(DatabaseMetaData metaData) {
        String productName = null;
        String name = null;
        String url = null;
        String user = null;
        try {
            System.out.println("--------------------------------");
            System.out.println("INFORMACION DE LA BASE DE DATOS");
            System.out.println("--------------------------------");

            productName = metaData.getDatabaseProductName();
            System.out.println("Nombre: " + productName);

            name = metaData.getDriverName();
            System.out.println("Driver: " + name);

            url = metaData.getURL();
            System.out.println("URL: " + url);

            user = metaData.getUserName();
            System.out.println("Usuario: " + user);
            System.out.println();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static ResultSet dataBaseTablesInfo(DatabaseMetaData metaData) {
        ResultSet resultSet = null;
        ResultSet rsColumn = null;
        try {
            resultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
            System.out.println("--------------------------------");
            System.out.println("INFORMACION DE LAS TABLAS");
            System.out.println("--------------------------------");

            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                System.out.println("Nombre de la tabla: " + tableName + "; " +
                        "Catalog: " + resultSet.getString("TABLE_CAT") + "; " +
                        "Schema: " + resultSet.getString("TABLE_SCHEM") + "; " +
                        "Type: " + resultSet.getString("TABLE_TYPE")
                );

                System.out.println("*** COLUMNAS de la TABLA " + tableName + " ***");

                rsColumn = metaData.getColumns(null, null, tableName, null);

                while (rsColumn.next()) {
                    System.out.println("Nombre de columna: " + rsColumn.getString("COLUMN_NAME") +
                            "; " + "Type: " + rsColumn.getString("TYPE_NAME") +
                            "; " + "IsNullable: " + rsColumn.getString("IS_NULLABLE")
                    );
                }
                System.out.println("--------------------------------");
                System.out.println();
            }
            rsColumn.close();
            resultSet.close();
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            return resultSet;
        }
    }


}
