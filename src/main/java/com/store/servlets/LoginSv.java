/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.store.servlets;

import com.store.entities.Account;
import com.store.persistence.controllers.PersistenceControllerFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author compi
 */
@WebServlet(name = "LoginSv", urlPatterns = {"/LoginSv"})
public class LoginSv extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String identifier = request.getParameter("identifierField");
        String password = request.getParameter("passwordField");
        
        Account account = PersistenceControllerFactory.getInstance()
                            .getAccountController().findAccount(identifier);
                        
        if ( account != null ) {
            
            if ( account.getPassword().equals(password) ) {
                System.out.println("Te haz logeado");
            } else {
                System.out.println("No te haz logeado");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
