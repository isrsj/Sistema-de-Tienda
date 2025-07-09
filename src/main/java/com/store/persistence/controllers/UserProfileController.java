
package com.tienda.persistence.controllers;

import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UserProfileController implements Serializable {

    public UserProfileController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
}
