package com.peaceathome.servlet;

import com.peaceathome.data.UsuarioDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Limpiamos espacios accidentales que envíe el formulario
        if (email != null) email = email.trim();
        if (password != null) password = password.trim();

        System.out.println("=== INTENTO DE LOGIN ===");
        System.out.println("Email recibido: [" + email + "]");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean credencialesValidas = usuarioDAO.iniciarSesion(email, password);

        if (credencialesValidas) {
            System.out.println("-> USUARIO ENCONTRADO Y CONTRASEÑA CORRECTA");
            HttpSession session = request.getSession();
            session.setAttribute("usuario", email);
            response.sendRedirect("dashboard.jsp");
        } else {
            System.out.println("-> CREDENCIALES INCORRECTAS");
            response.sendRedirect("index.html?error=invalid_credentials");
        }
    }
}