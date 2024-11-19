1. Crear constantes para rutas
```java
private static final String DB_PATH = "./database/schooldb";  
private static final String DEPARTMENTS_FILE = "./files/departments.txt";  
private static final String TEACHERS_FILE = "./files/teachers.txt";
```

2. Eliminar directorio database
```java
private static void eliminarDirectorioDatabase() {  
    File directory = new File("./database");  
    if (directory.exists()) {  
        for (File file : directory.listFiles()) {  
            file.delete();  
        }  
        directory.delete();  
    }  
}
```

3. Crear directorio
```java
// Función para crear el directorio de la base de datos  
private static void crearDirectorioDatabase() {  
    File directory = new File("./database");  
    if (!directory.exists()) {  
        directory.mkdir();  
    }  
}
```

4. Crear una conexion
```java
// Función para establecer la conexión con HSQLDB  
private static Connection establecerConexionDB() {  
    String url = "jdbc:hsqldb:" + DB_PATH;  
    try {  
        return DriverManager.getConnection(url);  
    } catch (SQLException e) {  
        System.out.println("No se pudo establecer conexión con la base de datos.");  
        return null;  
    }  
}
```

5. Crear y popular
```java
crearYPopularTabla(con, "departamentos", DEPARTMENTS_FILE, "Dept_num INT PRIMARY KEY, Name VARCHAR(20), Office VARCHAR(20)");
```

```java
// Método genérico para crear y popular una tabla desde un archivo  
private static void crearYPopularTabla(Connection con, String nombreTabla, String archivo, String estructuraTabla) {  
    try {  
        if (comprobarSiTablaExiste(con, nombreTabla)) {  
            vaciarTabla(con, nombreTabla);  
        } else {  
            crearTabla(con, nombreTabla, estructuraTabla);  
        }  
        insertarDatosDesdeArchivo(con, nombreTabla, archivo);  
    } catch (SQLException e) {  
        System.out.println("Error al crear y popular la tabla " + nombreTabla + ": " + e.getMessage());  
    }  
}
```

Comprobar tabla
```java
// Método para comprobar si una tabla ya existe  
private static boolean comprobarSiTablaExiste(Connection con, String nombreTabla) throws SQLException {  
    DatabaseMetaData dbMetaData = con.getMetaData();  
    ResultSet rs = dbMetaData.getTables(null, null, nombreTabla.toUpperCase(), null);  
    return rs.next();  
}
```

Vaciar tabla
```java
// Método para vaciar una tabla si ya existe  
private static void vaciarTabla(Connection con, String nombreTabla) throws SQLException {  
    try (Statement st = con.createStatement()) {  
        st.executeUpdate("DELETE FROM " + nombreTabla);  
        System.out.println("Contenido de la tabla '" + nombreTabla + "' eliminado.");  
    }  
}
```

Crear tabla
```java
// Método para crear una tabla con la estructura especificada  
private static void crearTabla(Connection con, String nombreTabla, String estructuraTabla) throws SQLException {  
    String createQuery = "CREATE TABLE " + nombreTabla + " (" + estructuraTabla + ")";  
    try (Statement st = con.createStatement()) {  
        st.executeUpdate(createQuery);  
        System.out.println("Tabla '" + nombreTabla + "' creada.");  
    }  
}
```

6. Insertar datos
```java
// Método para insertar datos en una tabla desde un archivo  
private static void insertarDatosDesdeArchivo(Connection con, String nombreTabla, String archivo) {  
    try (BufferedReader br = new BufferedReader(new FileReader(archivo));  
         Statement st = con.createStatement()) {  
  
        String linea;  
        int filasModificadas = 0;  
  
        while ((linea = br.readLine()) != null) {  
            String[] datos = linea.split(",");  
  
            // Convertir datos vacíos a null para insertar en la base de datos  
            for (int i = 0; i < datos.length; i++) {  
                datos[i] = datos[i].isEmpty() ? "NULL" : "'" + datos[i] + "'";  
            }  
  
            String query = "INSERT INTO " + nombreTabla + " VALUES (" + String.join(", ", datos) + ")";  
            st.executeUpdate(query);  
            filasModificadas++;  
        }  
  
        System.out.println("... " + filasModificadas + " filas modificadas en la tabla " + nombreTabla);  
    } catch (IOException e) {  
        System.out.println("Error al leer el archivo " + archivo + ": " + e.getMessage());  
    } catch (SQLException e) {  
        System.out.println("Error al insertar datos en la tabla " + nombreTabla + ": " + e.getMessage());  
    }  
}

```



8. Mostrar tabla
```java
mostrarTabla(con, "departamentos");
```

```java
// Método para mostrar el contenido de una tabla con una consulta SELECT  
private static void mostrarTabla(Connection con, String nombreTabla) {  
    String query = "SELECT * FROM " + nombreTabla;  
    try (Statement st = con.createStatement();  
         ResultSet rs = st.executeQuery(query)) {  
  
        ResultSetMetaData metaData = rs.getMetaData();  
        int columnCount = metaData.getColumnCount();  
  
        System.out.println("SELECT * from " + nombreTabla);  
        while (rs.next()) {  
            StringBuilder row = new StringBuilder("--> ");  
            for (int i = 1; i <= columnCount; i++) {  
                String columnName = metaData.getColumnName(i);  
                String columnValue = rs.getString(i);  
                row.append(columnName).append(": ").append(columnValue).append("; ");  
            }  
            System.out.println(row);  
        }  
    } catch (SQLException e) {  
        System.out.println("Error al mostrar el contenido de la tabla " + nombreTabla + ": " + e.getMessage());  
    }  
}
```