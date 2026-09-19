USE master;
GO

-- Crear login de SQL Server si no existe
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = 'peaceadmin')
BEGIN
    CREATE LOGIN peaceadmin WITH PASSWORD = 'Peace12345!';
END
GO

USE PeaceAtHome;
GO

-- Crear usuario dentro de la BD si no existe
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'peaceadmin')
BEGIN
    CREATE USER peaceadmin FOR LOGIN peaceadmin;
END
GO

-- Otorgar rol de propietario
ALTER ROLE db_owner ADD MEMBER peaceadmin;
GO