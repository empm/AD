package org.vawacode;

import java.sql.*;

/**
 * Pag. 8
 * Implementa una función estática para mostrar el contenido de un
 * ResultSet en una clase llamada JDBCHelper
 * --
 * for each row of the resultset, prints the content of each column
 * public static void showResultSet(ResultSet res)
 */

public class JDBCHelper {
    public static void main(String[] args) {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:actividad1/src/main/resources/bas.db");
            if (con != null)  System.out.println("Conexion establecida");

            DatabaseMetaData dmd = con.getMetaData();
            ResultSet rsTable = dmd.getTables(null, null, null, new String[]{"TABLE"});
            String tableName = rsTable.getString("TABLE_NAME");

            showResultSet(dmd.getColumns(null, null,tableName,null));


        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public static void showResultSet(ResultSet res){
        try {
            int i = 1;
            while (res.next()){
                System.out.println((i++) + ". " +
                        res.getString("COLUMN_NAME"));
            }
            res.close();
        } catch (SQLException e){
            e.getMessage();
        }


    }
}
