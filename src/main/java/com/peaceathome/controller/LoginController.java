package com.peaceathome.controller;

import com.peaceathome.data.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        boolean autenticado = usuarioDAO.iniciarSesion(correo, clave);

        if (autenticado) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", correo);
            response.sendRedirect("bienvenida.jsp");
        } else {
            request.setAttribute("error", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
}