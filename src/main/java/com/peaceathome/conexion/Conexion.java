package com.peaceathome.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;"
            + "databaseName=PeaceAtHome;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=30;";

    private static final String USER = "peaceadmin";
    private static final String PASSWORD = "Peace12345!";

    public static Connection conectar() {
        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos PeaceAtHome.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver JDBC de SQL Server no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos.");
            e.printStackTrace();
        }

        return con;
    }
}