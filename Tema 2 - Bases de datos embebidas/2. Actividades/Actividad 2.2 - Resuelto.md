## 1. CREAR UNA BASE DE DATOS CON HSQLDB

- [ ] Usa HSQLDB para crear una base de datos llamada `act2.2` en un directorio como `./Unit2/Act2.2.HSQLDB`
- Descargo el archivo y lo muevo a `/Library/Java/hsqldb` 
- Ejecuto el comando para iniciar sql y que se cree la base de datos llamada `Act2.2.HSQLDB`.
```bash
java -jar /Library/Java/hsqldb/lib/sqltool.jar --inlineRc=url=jdbc:hsqldb:"/Users/eperez/Documents/FP_DAM 24-25/1. Primer Trimestre/1. AD/U2 - Bases de datos embedidas/2. Ejercicios/Actividad2.2"/Act2.2.HSQLDB
```
- Compruebo que se crean varios archivos.
![[Captura de pantalla 2024-10-08 a las 21.14.39.png]]
- [ ] Crea las tablas Departamentos and Profesores, con la siguiente estructura, e inserta los mismos datos que en la `Act2.1` usando un fichero con los comandos SQL.
- Creo el  ficheros:
```bash
echo 
"CREATE TABLE departamentos (
Dept_num INT NOT NULL PRIMARY KEY,
Name VARCHAR(20),
Office VARCHAR(20));

CREATE TABLE profesores (
id INT NOT NULL PRIMARY KEY,
Name VARCHAR(15),
Surname V
Email VARCHAR(50),
Start_date DATE,
Dept_num INT,
FOREIGN KEY (Dept_num) REFERENCES departamentos(Dept_num));

INSERT INTO departamentos VALUES
(10, 'INFORMATICA', 'DESPA6'),
(20, 'COMERCIO', 'DESPA7'),
(30, 'ADMINISTRATIVO', 'DESPA8'),
(40, 'FOL', 'DESPA5');

INSERT INTO profesores VALUES
(1, 'Luz', 'Martinez', 'luz.martinez@iesabastos.org', '1990-01-01', 10),
(2, 'Cristina', 'Ausina', 'c.ausina@iesabastos.org', '1990-02-01', 10),
(3, 'Imma', 'Cabanes', 'i.cabanes@iesabastos.org', '1990-03-01', 10);

INSERT INTO profesores (id, name, surname, email, Dept_num)
VALUES (4, 'Mercedes', 'Sánchez', 'm.sanchez@iesabastos.org', 40);"
> datos.sql
```

- [ ] Teclea `\?` para obtener ayuda y ver como se ejecutan comandos desde un archivo externo.
- Para ejecutar un archivo externo, utilizo:
```bash
\i /Users/eperez/Documents/comandos_sql/datos.sql
```


- [ ] Recuerda ejecutar `commit;` antes de salir de la herramienta porque si no no se guardarán los cambios hasta que lo hagas.

```SQL
SELECT * FROM departments;
```
![[Captura de pantalla 2024-10-09 a las 7.37.53.png]]

## 2. Queries

Haz las siguientes queries:
1. Muestra todos los profesores en el despacho `DESPA6`.
```sql
SELECT p.id, p.Name, p.Surname, p.Email, p.Start_date, p.Dept_num, d.Office  FROM profesores p 
JOIN departamentos d ON p.dept_num = d.dept_num
WHERE d.office = "DESPA6";
```

2. Para todos los profesores del departamento de FOL, cambia su `Start_date` a `01/09/2000.`
```sql
UPDATE profesores
SET Start_date = '2000-09-01'
WHERE Dept_num = (SELECT Dept_num FROM departamentos WHERE Name = "FOL");
```

3. Muestra todos los profesores de FOL.
```sql
SELECT * FROM profesores
WHERE Dept_num = (SELECT Dept_num FROM departamentos WHERE Name = "FOL");
```

5. Añade una nueva columna llamada `Salario` a la tabla `profesores`.
```sql
ALTER TABLE profesores
ADD Salario INT;
```