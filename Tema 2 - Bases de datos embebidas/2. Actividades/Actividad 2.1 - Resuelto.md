## 1. CREAR UNA BASE DE DATOS CON SQLITE

- [ ] Usa SQLite para crear una base de datos llamada `act2.1.db` en un directorio como `./Unidad2/Act2.1-SQLite`.
- [ ] Crea las tablas Departamentos y Profesores, con la siguiente estructura:

| Departamentos |                 |     | Profesores |                             |
| ------------- | --------------- | --- | ---------- | --------------------------- |
| Dept_num      | Int Primary Key |     | id         | Int Primary Key             |
| Name          | 20 char         |     | Name       | 15 char                     |
| Office        | 20 char         |     | Surname    | 40 char                     |
|               |                 |     | Email      | 50 char                     |
|               |                 |     | Start_date | date                        |
|               |                 |     | Dept_num   | Referencia to Departamentos |

---
1. Actualmente estoy usando macOS Sonoma (14.6) por lo que `sqlite3` ya viene preinstalado.
2. Me dirijo a la ruta `/../1. AD/U2 - Bases de datos embedidas/2. Ejercicios/Actividad2.1/`
3. Abro una terminal en dicha carpeta.
4. Ejecuto el comando `sqlite3`
5. Ejecuto el comando `.open act2.1.db` para crear el archivo que contenga los datos que se proponen en el ejercicio.
6. A continuación creo la tabla de `Departamentos` usando lenguaje de SQL (DDL)
```SQL
CREATE TABLE departamentos (
Dept_num INT NOT NULL PRIMARY KEY,
Name VARCHAR(20),
Office VARCHAR(20));
```

7. Creo la tabla de `Profesores`
```SQL
CREATE TABLE profesores (
id INT NOT NULL PRIMARY KEY,
Name VARCHAR(15),
Surname VARCHAR(40),
Email VARCHAR(50),
Start_date DATE,
Dept_num INT,
FOREIGN KEY (Dept_num) REFERENCES departamentos(Dept_num));
```

---
## 2. INSERTAR DATOS

- [ ] Inserta estos datos en departamentos:

```
`10, INFORMATICA, DESPA6`
`20, COMERCIO, DESPA7`
`30, ADMINISTRATIVO, DESPA8`
`40, FOL, DESPA5`
```

- Código SQL empleado para insertar los datos:
```SQL
INSERT INTO departamentos
VALUES (10, 'INFORMATICA', 'DESPA6'),
(20, 'COMERCIO', 'DESPA7'),
(30, 'ADMINISTRATIVO', 'DESPA8'),
(40, 'FOL', 'DESPA5');
```
- Compruebo que se hayan aplicado correctamente los datos:
```SQL
SELECT * FROM departamentos;

10|INFORMATICA|DESPA6
20|COMERCIO|DESPA7
30|ADMINISTRATIVO|DESPA8
40|FOL|DESPA5
```

- [ ] Inserta estos datos en profesores:

```SQL
1, Luz Martinez, luz.martinez@iesabastos.org, 01/01/90
2, Cristina Ausina, c.ausina@iesabastos.org, 01/02/90
3, Imma Cabanes, i.cabanes@iesabastos.org, 01/03/90
4, Mercedes Sánchez, m.sanchez@iesabastos.org
```
 > Sabiendo que los profesores del 1 al 3 pertenecen al departamento de INFORMATICA y el 4 a FOL.
 
- Código SQL empleado para insertar los datos:
```SQL
INSERT INTO profesores
VALUES (1, 'Luz', 'Martinez', 'luz.martinez@iesabastos.org', '01/01/90', 10),
(2, 'Cristina', 'Ausina', 'c.ausina@iesabastos.org', '01/02/90', 10),
(3, 'Imma', 'Cabanes', 'i.cabanes@iesabastos.org', '01/03/90', 10);
```
```SQL
INSERT INTO profesores (id, name, surname, email, Dept_num)
VALUES (4, 'Mercedes', 'Sánchez', 'm.sanchez@iesabastos.org', 40);
```
---
## 3. QUERIES

Haz las siguientes queries:
- [ ] Todos los profesores del departamento de INFORMATICA.
```SQL
SELECT * FROM profesores 
WHERE Dept_num = 10;
```

- [ ] Para cada departamento, obtén todos los datos y el número de profesores que tiene.
```SQL
SELECT d.Dept_num, d.Name, d.Office, COUNT(p.id) AS Profesores
FROM departamentos d
LEFT JOIN profesores p ON d.Dept_num = p.Dept_num
GROUP BY d.Dept_num, d.Name, d.Office;
```

- [ ] Muestra el nombre y apellidos de todos los profesores y su departamento, ordenados alfabéticamente por su apellido.
```SQL
SELECT p.Name, p.Surname, p.Dept_num, d.Name AS Dept_name
FROM profesores p
LEFT JOIN departamentos d 
ON p.Dept_num = d.Dept_num
ORDER BY Surname ASC;
```

- [ ] Muestra los profesores (nombre, apellido y email) que empezaron hace mas de 20 años.
```SQL
SELECT Name, Surname, Email
FROM profesores
WHERE Start_date < '2004-01-01';
```
