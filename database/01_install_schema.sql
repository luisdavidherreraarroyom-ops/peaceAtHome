-- 1. Crear base de datos si no existe
IF DB_ID('PeaceAtHome') IS NULL
BEGIN
    CREATE DATABASE PeaceAtHome;
END
GO

USE PeaceAtHome;
GO

-- 2. Tabla Usuario
IF OBJECT_ID('Usuario', 'U') IS NULL
CREATE TABLE Usuario (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,
    telefono VARCHAR(20)
);
GO

-- 3. Tabla Agente
IF OBJECT_ID('Agente', 'U') IS NULL
CREATE TABLE Agente (
    id_agente INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario INT NOT NULL UNIQUE,
    codigo_agente VARCHAR(50),
    especialidad VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);
GO

-- 4. Tabla Cliente
IF OBJECT_ID('Cliente', 'U') IS NULL
CREATE TABLE Cliente (
    id_cliente INT IDENTITY(1,1) PRIMARY KEY,
    id_usuario INT NOT NULL UNIQUE,
    tipo_cliente VARCHAR(50),
    preferencias VARCHAR(MAX),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);
GO

-- 5. Tabla Propiedad
IF OBJECT_ID('Propiedad', 'U') IS NULL
CREATE TABLE Propiedad (
    id_propiedad INT IDENTITY(1,1) PRIMARY KEY,
    id_agente INT NOT NULL,
    direccion VARCHAR(200),
    precio DECIMAL(15,2),
    estado VARCHAR(50),
    descripcion VARCHAR(MAX),
    tipo VARCHAR(50),
    FOREIGN KEY (id_agente) REFERENCES Agente(id_agente)
);
GO

-- 6. Tabla Contrato
IF OBJECT_ID('Contrato', 'U') IS NULL
CREATE TABLE Contrato (
    id_contrato INT IDENTITY(1,1) PRIMARY KEY,
    id_propiedad INT NOT NULL,
    fecha_inicio DATE,
    fecha_fin DATE,
    valor DECIMAL(15,2),
    estado VARCHAR(50),
    FOREIGN KEY (id_propiedad) REFERENCES Propiedad(id_propiedad)
);
GO

-- 7. Tabla Visita
IF OBJECT_ID('Visita', 'U') IS NULL
CREATE TABLE Visita (
    id_visita INT IDENTITY(1,1) PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_propiedad INT NOT NULL,
    fecha DATE,
    hora TIME,
    estado VARCHAR(50),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_propiedad) REFERENCES Propiedad(id_propiedad)
);
GO