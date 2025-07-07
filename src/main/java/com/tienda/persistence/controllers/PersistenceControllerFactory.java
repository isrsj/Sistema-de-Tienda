
package com.tienda.persistence.controllers;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class PersistenceControllerFactory {
    
    private String persistenceUnitName = "tienda_persistence_unit";
    
    public AccountController getAccountController() {
        return new AccountController( createEntityManagerFactory( persistenceUnitName ) );
    }
    
    public AccountRoleController getAccountRoleController() {
        return new AccountRoleController( createEntityManagerFactory( persistenceUnitName ) );
    }
    
    public UserProfileController getUserProfileController() {
        return new UserProfileController( createEntityManagerFactory( persistenceUnitName ) );
    }
    
}
