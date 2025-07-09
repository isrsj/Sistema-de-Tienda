/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.tienda.servlets;

import com.tienda.persistence.controllers.PersistenceControllerFactory;
import jakarta.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author compi
 */
@WebListener
public class StartupServlet implements ServletContextListener {
    
     @Override
    public void contextInitialized(ServletContextEvent sce) {
        PersistenceControllerFactory.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory emf = PersistenceControllerFactory.getInstance().getManagerFactory();
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
