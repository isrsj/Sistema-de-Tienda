
package com.tienda.persistence.controllers;

import com.tienda.entities.Account;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;

public class AccountController implements Serializable {
        
    private EntityManager manager;
    
    public AccountController ( EntityManagerFactory managerFactory ) {
        manager = managerFactory.createEntityManager();
    }
    
    public Account findAccount(String identifier) {
        String hql = "where nickname=:paramNick";
        
        try {
            Account account = manager.createQuery(hql, Account.class)
                .setParameter("paramNick", identifier)
                .getSingleResult();
            
            return account;
        } catch (NoResultException e) {
            
            return null;
        }
        
    }
}
