
package com.tienda.servlets;

import com.tienda.persistence.controllers.PersistenceControllerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "StartupServlet", urlPatterns = {}, loadOnStartup=1)
public class StartupServlet extends HttpServlet {

   @Override
   public void init() throws ServletException {
       PersistenceControllerFactory.getInstance();
   }

}
