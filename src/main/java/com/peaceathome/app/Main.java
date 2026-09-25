package com.peaceathome.app;

import com.peaceathome.conexion.Conexion;
import com.peaceathome.data.UsuarioDAO;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Probando Conexión a la Base de Datos ===");
        
        try (Connection con = Conexion.conectar()) {
            if (con != null) {
                System.out.println("✅ Estado de la conexión: ACTIVA");
            } else {
                System.out.println("❌ Estado de la conexión: FALLIDA");
            }
        } catch (Exception e) {
            System.err.println("❌ Error durante la verificación: " + e.getMessage());
        }

        System.out.println("\n=== Probando Autenticación (UsuarioDAO) ===");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        // Credenciales de prueba
        String correoPrueba = "admin@peaceathome.com";
        String clavePrueba = "Admin123!";
        
        boolean loginExitoso = usuarioDAO.iniciarSesion(correoPrueba, clavePrueba);
        
        if (loginExitoso) {
            System.out.println("✅ Login exitoso para el usuario: " + correoPrueba);
        } else {
            System.out.println("⚠️ Credenciales incorrectas o usuario no registrado.");
        }
    }
}