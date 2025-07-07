
package com.tienda.persistence.controllers;

import jakarta.persistence.EntityManagerFactory;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class PersistenceControllerFactory {
    
    private static PersistenceControllerFactory instance = null;
    
    private EntityManagerFactory managerFactory = null;
    
    private PersistenceControllerFactory () {
        managerFactory = createEntityManagerFactory("tienda_persistence_unit");
    }
    
    public static PersistenceControllerFactory getInstance() {
        if ( instance == null ) {
            instance = new PersistenceControllerFactory();
        }
        return instance;
    }
    
    public AccountController getAccountController() {
        return AccountController.getInstance(managerFactory);
    }
    
    public AccountRoleController getAccountRoleController() {
        return new AccountRoleController( managerFactory );
    }
    
    public UserProfileController getUserProfileController() {
        return new UserProfileController( managerFactory );
    }
    
}
