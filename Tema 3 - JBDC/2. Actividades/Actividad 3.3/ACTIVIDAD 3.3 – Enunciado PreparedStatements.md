# 1. CREANDO UNA BASE DE DATOS DESDE UN FICHERO DE BACKUP

> Implementa un programa que cree una nueva base de datos desde un archivo de backup en formato CSV. 
> La estructura de las tablas es conocida (el mismo que en las actividades anteriores): departamentos y profesores.

Utiliza HSQLDB para crear la base de datos. 
El programa debe seguir los siguientes pasos:
1. Comprobar si el directorio de la base de datos existe. Si es así, eliminarlo. De esta manera creamos la base de datos y las tablas cada vez que ejecutamos el programa sin problemas. Nota: File solo borra un directorio cuando está vacío, así que primero deberemos borrar todos los ficheros contenidos en el directorio.
 2. Crear la base de datos en el directorio .\database
3. Crear la tabla departamentos y rellenarla con los datos contenidos en .\files\departments.txt. Muestra el número de filas modificadas.
4. Mostrar el contenido de la tabla Departamentos usando una query SELECT.
5. Crear la tabla de profesores y rellenarla con los datos contenidos en .\files\teachers.txt. Muestra el número de filas modificadas.
6. Mostrar el contenido de la tabla Profesores usando una query SELECT

La salida del programa debería ser la siguiente:
```bash
Borrando base de datos
Creando base de datos y conectando
Creando y rellenando tabla departamentos...
... 4 filas modificadas
SELECT * from departments
--> DEPT_NUM: 10; NAME: INFORMATICA; OFFICE: DESPA6;
--> DEPT_NUM: 20; NAME: COMERCIO; OFFICE: DESPA7;
--> DEPT_NUM: 30; NAME: ADMINISTRATIVO; OFFICE: DESPA8;
--> DEPT_NUM: 40; NAME: FOL; OFFICE: DESPA5;
Creando y rellenando tabla profesores... 
...4 filas modificadas
SELECT * from teachers 
--> ID: 1; NAME: Luz; SURNAME: Martinez; EMAIL: luz.martinez@iesabastos.org; START_DATE: 1990-01-01; DEPT_NUM: 10;
--> ID: 2; NAME: Cristina; SURNAME: Ausina; EMAIL: c.ausina@iesabastos.org; START_DATE: 1990-02-01; DEPT_NUM: 10;
--> ID: 3; NAME: Imma; SURNAME: Cabanes; EMAIL: i.cabanes@iesabastos.org; START_DATE: 1990-03-01; DEPT_NUM: 10;
--> ID: 4; NAME: Mercedes; SURNAME: Sánchez; EMAIL: m.sanchez@iesabastos.org; START_DATE: null; DEPT_NUM: 40;
```

> Recuerda cerrar todos los resultSets, statements y connections cuando no se necesiten mas. Cuidado: Los valores en el archivo pueden estar vacíos. Estos valores no se leerán como un string vacío, se guardarán como null en la base de datos.

### Ampliación:
> Reutiliza codigo: usa una función genérica para crear y popular la tabla desde un fichero. ¿Qué información debería ser agregada al fichero?

> En lugar de borrar la base de datos si existe, comprueba si la tabla existe en la base de datos y borra sus contenidos sin crear la tabla de nuevo.




---
Para abordar este tipo de ejercicios, necesitas trabajar con los siguientes conceptos y herramientas de Java y JDBC:

1. **Comprobación y gestión de archivos y directorios**:
   - Usa la clase `File` para verificar si un directorio existe y para eliminar archivos dentro de él.
   - Métodos útiles: `exists()`, `delete()` (recuerda borrar archivos individualmente antes de borrar el directorio).

2. **Conexión a la base de datos**:
   - Usa `DriverManager.getConnection` para conectar con HSQLDB en un directorio específico.

3. **Creación de tablas y PreparedStatements**:
   - Crea la estructura de tablas según el esquema dado en el CSV.
   - Usa `PreparedStatement` para realizar consultas SQL y manejar datos variables de forma segura.
   - Para contar filas afectadas, usa `executeUpdate()`.

4. **Lectura de archivos y asignación de valores NULL**:
   - Abre el archivo CSV usando `FileReader` o `BufferedReader`.
   - Detecta valores vacíos en el archivo y establece `null` en vez de una cadena vacía.

5. **Consultas y visualización de datos**:
   - Ejecuta `SELECT` para obtener los datos y despliega resultados con `ResultSet`.

6. **Gestión de recursos**:
   - Asegúrate de cerrar `ResultSet`, `Statement` y `Connection` al terminar para evitar fugas de recursos.
