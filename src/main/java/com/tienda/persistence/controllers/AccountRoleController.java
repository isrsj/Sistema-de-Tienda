/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.persistence.controllers;

import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.tienda.entities.Account;
import com.tienda.entities.AccountRole;
import com.tienda.persistence.controllers.exceptions.IllegalOrphanException;
import com.tienda.persistence.controllers.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author compi
 */
public class AccountRoleController implements Serializable {

    public AccountRoleController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccountRole accountRole) {
        if (accountRole.getAccount() == null) {
            accountRole.setAccount(new HashSet<Account>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Account> attachedAccount = new HashSet<Account>();
            for (Account accountAccountToAttach : accountRole.getAccount()) {
                accountAccountToAttach = em.getReference(accountAccountToAttach.getClass(), accountAccountToAttach.getId());
                attachedAccount.add(accountAccountToAttach);
            }
            accountRole.setAccount(attachedAccount);
            em.persist(accountRole);
            for (Account accountAccount : accountRole.getAccount()) {
                AccountRole oldRoleOfAccountAccount = accountAccount.getRole();
                accountAccount.setRole(accountRole);
                accountAccount = em.merge(accountAccount);
                if (oldRoleOfAccountAccount != null) {
                    oldRoleOfAccountAccount.getAccount().remove(accountAccount);
                    oldRoleOfAccountAccount = em.merge(oldRoleOfAccountAccount);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccountRole accountRole) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccountRole persistentAccountRole = em.find(AccountRole.class, accountRole.getId());
            Set<Account> accountOld = persistentAccountRole.getAccount();
            Set<Account> accountNew = accountRole.getAccount();
            List<String> illegalOrphanMessages = null;
            for (Account accountOldAccount : accountOld) {
                if (!accountNew.contains(accountOldAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Account " + accountOldAccount + " since its role field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Account> attachedAccountNew = new HashSet<Account>();
            for (Account accountNewAccountToAttach : accountNew) {
                accountNewAccountToAttach = em.getReference(accountNewAccountToAttach.getClass(), accountNewAccountToAttach.getId());
                attachedAccountNew.add(accountNewAccountToAttach);
            }
            accountNew = attachedAccountNew;
            accountRole.setAccount(accountNew);
            accountRole = em.merge(accountRole);
            for (Account accountNewAccount : accountNew) {
                if (!accountOld.contains(accountNewAccount)) {
                    AccountRole oldRoleOfAccountNewAccount = accountNewAccount.getRole();
                    accountNewAccount.setRole(accountRole);
                    accountNewAccount = em.merge(accountNewAccount);
                    if (oldRoleOfAccountNewAccount != null && !oldRoleOfAccountNewAccount.equals(accountRole)) {
                        oldRoleOfAccountNewAccount.getAccount().remove(accountNewAccount);
                        oldRoleOfAccountNewAccount = em.merge(oldRoleOfAccountNewAccount);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = accountRole.getId();
                if (findAccountRole(id) == null) {
                    throw new NonexistentEntityException("The accountRole with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AccountRole accountRole;
            try {
                accountRole = em.getReference(AccountRole.class, id);
                accountRole.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accountRole with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Account> accountOrphanCheck = accountRole.getAccount();
            for (Account accountOrphanCheckAccount : accountOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AccountRole (" + accountRole + ") cannot be destroyed since the Account " + accountOrphanCheckAccount + " in its account field has a non-nullable role field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(accountRole);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AccountRole> findAccountRoleEntities() {
        return findAccountRoleEntities(true, -1, -1);
    }

    public List<AccountRole> findAccountRoleEntities(int maxResults, int firstResult) {
        return findAccountRoleEntities(false, maxResults, firstResult);
    }

    private List<AccountRole> findAccountRoleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccountRole.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AccountRole findAccountRole(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccountRole.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountRoleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccountRole> rt = cq.from(AccountRole.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
