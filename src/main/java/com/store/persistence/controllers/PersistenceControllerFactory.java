
package com.store.persistence.controllers;

import jakarta.persistence.EntityManagerFactory;
import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class PersistenceControllerFactory {
    
    private static PersistenceControllerFactory instance = null;
    
    private EntityManagerFactory managerFactory = null;
    
    private PersistenceControllerFactory () {
        managerFactory = createEntityManagerFactory("store_unit");
    }
    
    public static PersistenceControllerFactory getInstance() {
        if ( instance == null ) {
            instance = new PersistenceControllerFactory();
        }
        return instance;
    }

    public EntityManagerFactory getManagerFactory() {
        return managerFactory;
    }
    
    public AccountController getAccountController() {
        return new AccountController( managerFactory );
    }
    
    public AccountRoleController getAccountRoleController() {
        return new AccountRoleController( managerFactory );
    }
    
    public UserProfileController getUserProfileController() {
        return new UserProfileController( managerFactory );
    }
    
}
