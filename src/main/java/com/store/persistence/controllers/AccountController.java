
package com.store.persistence.controllers;

import com.store.entities.Account;
import com.store.session.Session;
import java.io.Serializable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.time.LocalDateTime;

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
    
    public Account createAccountEntity(String nickname, String email, String password) {
        return new Account(nickname, email, password, LocalDateTime.now(), null, null);
    }
    
    public void persistAccount(Account account) {
        Session.inSession(manager, entityManager -> {
            manager.persist(account);
        });
    }
}
