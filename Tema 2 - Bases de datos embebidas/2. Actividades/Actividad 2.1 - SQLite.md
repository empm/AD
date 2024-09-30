> El objetivo de esta actividad es practicar la instalación de SQLite y manejarlo en modo consola, desde la ventana de comandos.

> **Sube un documento con el código y las explicaciones de como se resuelve la actividad. Sube también el fichero de la base de datos.**

## 1. CREAR UNA BASE DE DATOS CON SQLITE

- Usa SQLite para crear una base de datos llamada `act2.1.db` en un directorio como `./Unidad2/Act2.1-SQLite`.
- Crea las tablas Departamentos y Profesores, con la siguiente estructura:

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


## 2. INSERTAR DATOS

- Inserta estos datos en departamentos:

```SQL
`10, INFORMATICA, DESPA6`
`20, COMERCIO, DESPA7`
`30, ADMINISTRATIVO, DESPA8`
`40, FOL, DESPA5`
```

- Inserta estos datos en profesores:

```SQL
1, Luz Martinez, luz.martinez@iesabastos.org, 01/01/90
2, Cristina Ausina, c.ausina@iesabastos.org, 01/02/90
3, Imma Cabanes, i.cabanes@iesabastos.org, 01/03/90
4, Mercedes Sánchez, m.sanchez@iesabastos.org
```

- Sabiendo que los profesores del 1 al 3 pertenecen al departamento de INFORMATICA y el 4 a FOL


## 3. QUERIES

Haz las siguientes queries:
1. Todos los profesores del departamento de INFORMATICA.
2. Para cada departamento, obtén todos los datos y el número de profesores que tiene.
3. Muestra el nombre y apellidos de todos los profesores y su departamento, ordenados
alfabéticamente por su apellido.
4. Muestra los profesores (nombre, apellido y email) que empezaron hace mas de 20 años.