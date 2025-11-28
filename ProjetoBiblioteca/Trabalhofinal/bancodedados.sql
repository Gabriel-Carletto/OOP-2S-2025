CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

CREATE TABLE pessoas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE usuarios (
    id INT PRIMARY KEY,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    FOREIGN KEY (id) REFERENCES pessoas(id)
);

CREATE TABLE funcionarios (
    id INT PRIMARY KEY,
    cargo VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2),
    FOREIGN KEY (id) REFERENCES pessoas(id)
);