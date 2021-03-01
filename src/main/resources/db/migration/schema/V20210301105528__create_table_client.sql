CREATE TABLE tb_client (
    id int8 NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age int4 NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    sexo VARCHAR(50) NOT NULL,
    specificID uuid NOT NULL
);