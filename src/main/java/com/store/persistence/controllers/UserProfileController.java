
package com.store.persistence.controllers;

import com.store.entities.UserProfile;
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
    
    public UserProfile createUserProfileEntity( String name, String paternal, String maternal, String phone ) {
        return new UserProfile(name, paternal, maternal, phone, null);
    }
}
