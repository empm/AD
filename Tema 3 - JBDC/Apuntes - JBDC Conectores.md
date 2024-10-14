## Conectores
Son los elementos que nos permiten el acceso a una base de datos desde un fragmento de código.

### Protocolos
Son los sistemas que permite la conexión con las bases de datos.
Antes, cada sistema de base de datos, tenía su propio protocolo. Ahora existen protocolos estandarizados.

#### ODBC
- Creado por Microsoft
- No recomendado para Linux/Unix

#### JDBC (Java Data Base Connectivity)
- Nos permite conectar con muchos DBMS usando Java.

## Conexión mediante JDBC 

Componentes API
1. Driver manager  `java.sql.DriverManager`
2. Conexión a la DB  `java.sql.Connection`
3. Comando a ejecutar  `java.sql.Statement`
4. Resultado  `java.sql.ResultSet`

### 1. **DriverManager** 
El **DriverManager** es el encargado de manejar y cargar los controladores necesarios para conectar Java con una base de datos específica (como MySQL, PostgreSQL, etc.). 

> **DriverManager**, es quien se encarga de establecer la conexión con la base de datos.

### 2. **Connection**
La **conexión** es el enlace entre la aplicación Java y la base de datos. 
Mediante este objeto, tu aplicación puede comunicarse con la base de datos y realizar operaciones. Importante, cerrar la conexión con `db.close()`.

> **Connection** es quien establece mediante protocolos acceso y conexión a la base de datos.

MySQL: 
- `jdbc`: protocolo
- `mysql`: subprotocolo (nombre del driver) (dependencia que importamos)
- `//localhost/profesores`: ubicación y nombre de la db (ver en documentación)

Poner la db en la raíz del proyecto

```Java
jdbc:mysql://127.0.0.1/profresores
```

SQLite:

```Java
jdbc:sqlite:profresores
```

### 3. **Statement**
El **Statement** es el comando que envías a la base de datos para ejecutar consultas o modificar datos. Es como enviar una instrucción SQL a la base de datos desde tu programa.

> **Statement** es el encargado de enviar una consulta SQL a una de las tablas.

## 4. **ResultSet**
El **ResultSet** es el conjunto de resultados devuelto por una consulta. 
Si ejecutas una consulta para obtener datos, los resultados se almacenan en un `ResultSet`, que puedes recorrer para acceder a la información.

> **ResultSet** contiene el resultado de la consulta, que se recorre para mostrar la información devuelta por varias columnas.
---
### Ejemplo de código (conexión y consulta):

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        
        Connection con = null;
        try {
            // 1. DriverManager: Establecer la conexión con la base de datos
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mi_base_datos", "usuario", "contraseña");

            // 2. Statement: Crear un objeto para ejecutar comandos SQL
            Statement stmt = con.createStatement();

            // 3. Ejecutar una consulta y obtener el resultado en un ResultSet
            ResultSet rs = stmt.executeQuery("SELECT * FROM profesores");

            // 4. Recorrer el ResultSet para ver los datos obtenidos
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString("Name") + " - Email: " + rs.getString("Email"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
	        if (con != null) {
		        try {
			        // Cerrar la conexión cuando termines
		            con.close();
		        } catch (SQLException e) {
				    e.printStackTrace();
		        }
	        }
        }
    }
}
```


## Metadatos
Son los datos (la información) de los datos.

Son importantes ya que no tenemos por que saber cómo está hecha la base de datos. Es posible que no la hayamos hecho nosotros.

```Java
DatabaseMetaData dbmd = miconexion.getMetaData();
```


### Métodos DatabaseMetaData para recoger información:
 - `.getDatabaseProductName()
 - `.getDriverName()
 - `.getDriverVersion()
 - `.getURL()
 - `.getUserName()

Todos devolverán un String.

#### Información de las tablas `getTables()`

```Java
ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
```

#### Parámetros de búsqueda:
- **Catalog** = null → todos los catálogos
- **schemaPattern** = null → todos los esquemas
- **tableNamePattern** = null → todos los nombres de tabla
- **types** = null → todos los tipos

#### getTables devuelve ResultSet
- Cada fila de ResultSet es la descripción de una tabla
- Las columnas se pueden indexar por nombre o por número:
	- resul.getString(1);
	- resul.getString("TABLE_CAT");

#### Nombres de las columnas del ResultSet:

1. TABLE_CAT String => catálogo de tablas (puede ser null)
2. TABLE_SCHEM String => esquema de tablas (puede ser null)
3. TABLE_NAME String => nombre de la tabla
4. TABLE_TYPE String => Tipo de tabla. Los tipos típicos son: 
	TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
5. REMARKS String => explanatory comment on the table

To-do
- [ ] pag. 18