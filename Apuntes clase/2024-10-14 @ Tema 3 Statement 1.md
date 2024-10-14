
Hay tres tipos de statements. Cada uno se usa para una cosa en concreto, uno solo para consultas sin modificar nada, otro modificando...

ResultSet es un array de valores

metodos getXXX()

Statement son estáticos

```Java
Statement stt = con.createStatement();
```

```Java
Statement stt = conexion.createStatement();
String query = "SELECT * FROM teachers WHERE nombre='Pedro'";

ResultSet rs = stt.executeQuery(query);
```



# Actividad

Crear una clase que luego guardaremos para añadir más funciones
JDBCHelper

cada fila de resulSet


 En JDBCHelper, implementa una función estática para saber si

el nombre de una tabla existe en una base de datos. Se ve con los metadatos del primer bloque

Tercer parámetro que usamos para saber si una tabla existe (devolvemos true y si no existe nada)



Metadatos, si no existe la creamos
Statement stt = conexion.createStatement();

String query = "SELECT * FROM teachers

WHERE nombre='Pedro'";

ResultSet rs = stt.executeQuery(query);
![[Pasted image 20241014193625.png]]


Dos updates
dos 