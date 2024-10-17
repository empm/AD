CREATE TABLE departamentos (
    Dept_num INT NOT NULL PRIMARY KEY,
    Name VARCHAR(20),
    Office VARCHAR(20)
);

CREATE TABLE profesores (
    id INT NOT NULL PRIMARY KEY,
    Name VARCHAR(15),
    Surname VARCHAR(40),
    Email VARCHAR(50),
    Start_date DATE,
    Dept_num INT,
    FOREIGN KEY (Dept_num) REFERENCES departamentos(Dept_num)
);

INSERT INTO departamentos
VALUES 
(10, 'INFORMATICA', 'DESPA6'),
(20, 'COMERCIO', 'DESPA7'),
(30, 'ADMINISTRATIVO', 'DESPA8'),
(40, 'FOL', 'DESPA5');

INSERT INTO profesores
VALUES 
(1, 'Luz', 'Martinez', 'luz.martinez@iesabastos.org', '1990-01-01', 10),
(2, 'Cristina', 'Ausina', 'c.ausina@iesabastos.org', '1990-02-01', 10),
(3, 'Imma', 'Cabanes', 'i.cabanes@iesabastos.org', '1990-03-01', 10);

INSERT INTO profesores (id, name, surname, email, Dept_num)
VALUES (4, 'Mercedes', 'Sánchez', 'm.sanchez@iesabastos.org', 40);