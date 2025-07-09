
package com.store.persistence.controllers;

import com.store.entities.AccountRole;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashSet;

public class AccountRoleController implements Serializable {

    public AccountRoleController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AccountRole createAccountRoleEntity(Integer id, String name) {
        return new AccountRole(id, name, new HashSet<>());
    }
    
}
