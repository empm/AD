# Bases de datos Relacionales 
- Formadas por Tablas que se relacionan entre sí.
### Tablas (BD) > Clases (POO)
- Es una estructura que define un conjunto de columnas y contiene datos organizados en filas. Cada fila representa una instancia de un conjunto de datos.
### Columnas (BD) > Atributos (POO)
- Cada columna en una tabla almacena un tipo específico de dato (por ejemplo, nombres, edades, correos) y representa un aspecto de la información que la tabla contiene.
### Dominio (BD) > Tipo de dato (POO)
- El dominio es el conjunto de valores permitidos para una columna, o simplemente el tipo de dato que esa columna puede almacenar (por ejemplo, INTEGER, VARCHAR, DATE).
### Registro / Fila (BD) > Objeto (POO)
- Representa un conjunto de valores que corresponden a los atributos (columnas) definidos en la tabla.

### Primary Key (PK)
- Identificador único de la fila de una tabla.
#### Restricción
- Valor único
- No nulo
- Valor de FK debe corresponder con la PK de la tabla a la que se relaciona

### Foreing Key (FK)
- Permite relacionar datos de distintas tablas. Incluir la PK de una tabla en otra para poder relacionarla.

## Modelo Relacional
- Relación 1 - 1
- Relación 1 - N
- Relación N - M

## Usos SQL
- DDL: Descripción de datos
- DML: Manipulación de datos
- DCL: Control de acceso a los datos

## Acceso a datos
- Consiste en recoger y manipular datos de un origen local o remoto.
	- Bases de datos relacionales
	- Bases de datos no relacionales
	- Fuentes: XML, JSON, Excel...

## Bases de datos embebidas
- No tienen servidor
- Se guardan en archivos locales
- El motor de la db está embebido en la app
	- Arranca cuando inicia la app y se para cuando la app se cierra
- Ejemplos:
	- SQLite, Derby, HSQLDB, eXtremeDB...

### Modo de uso
- Descargar programa
- Ejecutar .exe
- Ejecutar comandos SQL ;
- Cerrar programa

### SQLite
- Descarga: http://www.sqlite.org/download.html
- Ejemplo:

```SQL
INSERT INTO departamentos VALUES (1, 'SOFTWARE', 'VALENCIA');

SELECT * FROM DEPARTAMENTOS;  
1|SOFTWARE|VALENCIA  

INSERT INTO departamentos VALUES (2, 'VENTAS', 'MADRID');  
INSERT INTO departamentos VALUES (3, 'ADMINISTRACION', 'MADRID'); 
INSERT INTO departamentos VALUES (4, 'INGENIERIA', 'VALENCIA');

SELECT * FROM DEPARTAMENTOS;
1|SOFTWARE|VALENCIA 
2|VENTAS|MADRID 
3|ADMINISTRACION|MADRID 
4|INGENIERIA|VALENCIA

.quit

.help -- Lista de comandos útiles
```

#### FK en  SQLite
- Hasta la versión 3.6.19 (2009) no había soporte para las FK
- Para comprobar si están activadas:
```SQL
PRAGMA foreign_keys;
```
- Para activarlas:
```SQL
PRAGMA foreign_keys = 1;
```


## HSQLDB
- De código abierto
- Implementada en Java
- Solo se puede usar con JVM
- Integra JDBC
	- Es más lenta que SQLite pero más completa (Procedures, DATE type, vistas...)
- Documentación: https://hsqldb.org/doc/2.0/guide/index.html
- Comandos
```SQL
\q -- para salir    
\? -- listado de comandos    
\dt -- mostrar tablas    
\i path/file.sql -- Ejecuta comandos desde un fichero externo.
```

### Modo de uso
- Crear tabla
- Insertar datos
- Usar `commit;`
- Cerrar y volver a abrir
