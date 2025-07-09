
package com.store.servlets;

import com.store.entities.Account;
import com.store.entities.AccountRole;
import com.store.entities.UserProfile;
import com.store.persistence.controllers.AccountController;
import com.store.persistence.controllers.AccountRoleController;
import com.store.persistence.controllers.PersistenceControllerFactory;
import com.store.persistence.controllers.UserProfileController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet( name="CreateAccountServlet", urlPatterns ={"/CreateAccountServlet"} )
public class CreateAccountServlet extends HttpServlet{
    
    private PersistenceControllerFactory persistenceController = PersistenceControllerFactory.getInstance();
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name = request.getParameter("nameField");
        String paternal = request.getParameter("paternalLastnameField");
        String maternal = request.getParameter("maternalLastnameField");
        String phone = request.getParameter("phoneNumberField");
        String nickname = request.getParameter("nicknameField");
        String email = request.getParameter("emailField");
        String password = request.getParameter("passwordField");
        
        UserProfileController profileController = persistenceController.getUserProfileController();
        UserProfile profileEntity = profileController.createUserProfileEntity(name, paternal, maternal, phone);
        
        AccountController accountController = persistenceController.getAccountController();
        Account accountEntity = accountController.createAccountEntity(nickname, email, password);
        
        AccountRoleController roleController = persistenceController.getAccountRoleController();
        AccountRole roleEntity = roleController.createAccountRoleEntity(Integer.valueOf("1"), "Customer");
        
        accountEntity.setUserProfile(profileEntity);
        accountEntity.setRole(roleEntity);
        
        profileEntity.setAccount(accountEntity);
        roleEntity.setSingleAccount(accountEntity);
        
        accountController.persistAccount(accountEntity);
    }
    
    
    
}
