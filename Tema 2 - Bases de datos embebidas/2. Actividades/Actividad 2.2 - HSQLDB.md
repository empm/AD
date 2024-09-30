> El objetivo de esta actividad es entender las similitudes entre la instalación y el trabajo con diferentes DBMS en la consola de comandos.

> **Sube un fichero con el código y explicaciones de como vas resolviendo la actividad, sube también el fichero de la base de datos**


## 1. CREAR UNA BASE DE DATOS CON HSQLDB

- Usa HSQLDB para crear una base de datos llamada `act2.2` en un directorio como `./Unit2/Act2.2.HSQLDB`
- Crea las tablas Departamentos and Profesores, con la siguiente estructura, e inserta los mismos datos que en la `Act2.1` usando un fichero con los comandos SQL.
- Teclea `\?` para obtener ayuda y ver como se ejecutan comandos desde un archivo externo.

| Departamentos |                 |
| ------------- | --------------- |
| Dept_num      | Int Primary Key |
| Name          | 20 char         |
| Office        | 20 char         |

| Profesores |                             |
| ---------- | --------------------------- |
| id         | Int Primary Key             |
| Name       | 15 char                     |
| Surname    | 40 char                     |
| Email      | 50 char                     |
| Start_date | date                        |
| Dept_num   | Referencia to Departamentos |

- Recuerda ejecutar `commit;` antes de salir de la herramienta porque si no no se guardarán los cambios hasta que lo hagas.

```SQL
SELECT * FROM departments;
```


## 2. Queries

Haz las siguientes queries:
1. Muestra todos los profesores en el despacho `DESPA6`.
2. Para todos los profesores del departamento de FOL, cambia su `Start_date` a `01/09/2000.`
3. Muestra todos los profesores de FOL.
4. Añade una nueva columna llamada `Salario` a la tabla `profesores`.