
package com.tienda.persistence.controllers;

import com.tienda.entities.Account;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

public class AccountController implements Serializable {
    
    private EntityManagerFactory emf = null;
    
    public AccountController (EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Account findAccount(String identifier) {
        EntityManager em = getEntityManager();
        String hql = "where nickname=:paramNick";
        
        try {
            Account account = em.createQuery(hql, Account.class)
                .setParameter("paramNick", identifier)
                .getSingleResult();
            
            return account;
        } catch (NoResultException e) {
            
            return null;
        }
        
    }
}
