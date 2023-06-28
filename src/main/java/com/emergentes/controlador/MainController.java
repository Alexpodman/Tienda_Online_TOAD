/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.emergentes.controlador;

import com.emergentes.bean.BeanAcceso;
import com.emergentes.dao.UsuarioJpaController;
import com.emergentes.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        if (opcion != null) {
            if (opcion.equals("editarUsuario")) {
                
            } else if (opcion.equals("")) {
                
            } else if (opcion.equals("")) {
                
            } else if (opcion.equals("")) {
                
            } else if (opcion.equals("")) {
                
            } else if (opcion.equals("")) {
                
            } else if (opcion.equals("")) {
                
            } else if (opcion.equals("")) {
                
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        BeanAcceso dao = new BeanAcceso();
        List<Usuario> listaUsuarios = dao.getDaoUsuarios().findUsuarioEntities();
        for (Usuario u : listaUsuarios) {
            System.out.println("nombre: " + u.getNombre());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
