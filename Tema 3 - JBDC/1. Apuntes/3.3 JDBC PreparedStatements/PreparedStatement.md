
Derivado del [Statement](0.%20Statements.md). Hay dos diferencias principales:
- La frase está pre-compilada por el DBMS
- Acepta parámetros

Igual que un [Statement](0.%20Statements.md#Métodos), antes de usarlo, debemos crearlo:
- La creación depende de una conexión abierta
- `PreparedStatement stt = con.prepareStatement(String sql);`

Cuando terminamos, debemos cerrar el [Statement](0.%20Statements.md):
- `.close();`

## Explicacion
Para realizar los 4 puntos en JDBC:

1. **Establecer conexión**: 
   - **Objeto**: `Connection`
   - **Método**: `DriverManager.getConnection("jdbc:sqlite:tuBase.db")` crea una conexión con la base de datos.

2. **Crear tabla "alumnos"**: 
   - **Objeto**: `Statement`
   - **Métodos**: 
     - Usar `connection.createStatement()` para obtener el `Statement`.
     - Ejecutar `statement.executeUpdate("CREATE TABLE alumnos (id INT, nombre TEXT);")` para crear la tabla.

3. **Insertar datos**: 
   - **Objeto**: `Statement` o `PreparedStatement`
   - **Método**: `executeUpdate("INSERT INTO alumnos VALUES (1, 'Juan');")` inserta un registro.

4. **Consultar nombres**:
   - **Objeto**: `ResultSet`
   - **Métodos**:
	   - Usar `PreparedStatement`para crear un `preparedStatement(query)`
	   - `String query = "SELECT * FROM alumnos WHERE id = ?";`
	   - Setear los datos `?`
		   - Dependiendo del tipo de dato usaremos un método u otro
		   - En este caso, queremos filtrar por id que es un entero
		   - `ps.setInt(indice, valor)`
			   - Indice: es la posición de la ocurrencia de `?`, es decir si existen mas de una `?`, la primera que salga será el 1 y así sucesivamente
			   - Valor: el es dato dentro del id que queremos filtrar
     - Ejecutar `ResultSet rs = statement.executeQuery();`
	     - En este caso `executeQuery` no acepta ningún parámetro. 
	     - A diferencia con `Statement`que aceptaba el parámetro de query.

# Ejemplo
```java
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
                String query = "SELECT * FROM alumnos" +
                "WHERE name = ? OR id = ?;";  
                
                PreparedStatement ps = con.prepareStatement(query);  
                ps.setString(1, "Enrique");  
                ps.setInt(2, 2);  
                ResultSet rs = ps.executeQuery();  
                while (rs.next()) {  
                    int id = rs.getInt("id");  
                    String name = rs.getString("name");  
                    System.out.println("Numero de alumno: " 
                    + id + ". Alumno: " + name);  
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
```