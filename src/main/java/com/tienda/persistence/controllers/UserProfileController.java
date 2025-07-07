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
import com.tienda.entities.UserProfile;
import com.tienda.persistence.controllers.exceptions.IllegalOrphanException;
import com.tienda.persistence.controllers.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author compi
 */
public class UserProfileController implements Serializable {

    public UserProfileController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UserProfile userProfile) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Account account = userProfile.getAccount();
            if (account != null) {
                account = em.getReference(account.getClass(), account.getId());
                userProfile.setAccount(account);
            }
            em.persist(userProfile);
            if (account != null) {
                UserProfile oldUserProfileOfAccount = account.getUserProfile();
                if (oldUserProfileOfAccount != null) {
                    oldUserProfileOfAccount.setAccount(null);
                    oldUserProfileOfAccount = em.merge(oldUserProfileOfAccount);
                }
                account.setUserProfile(userProfile);
                account = em.merge(account);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UserProfile userProfile) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UserProfile persistentUserProfile = em.find(UserProfile.class, userProfile.getId());
            Account accountOld = persistentUserProfile.getAccount();
            Account accountNew = userProfile.getAccount();
            List<String> illegalOrphanMessages = null;
            if (accountOld != null && !accountOld.equals(accountNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Account " + accountOld + " since its userProfile field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (accountNew != null) {
                accountNew = em.getReference(accountNew.getClass(), accountNew.getId());
                userProfile.setAccount(accountNew);
            }
            userProfile = em.merge(userProfile);
            if (accountNew != null && !accountNew.equals(accountOld)) {
                UserProfile oldUserProfileOfAccount = accountNew.getUserProfile();
                if (oldUserProfileOfAccount != null) {
                    oldUserProfileOfAccount.setAccount(null);
                    oldUserProfileOfAccount = em.merge(oldUserProfileOfAccount);
                }
                accountNew.setUserProfile(userProfile);
                accountNew = em.merge(accountNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userProfile.getId();
                if (findUserProfile(id) == null) {
                    throw new NonexistentEntityException("The userProfile with id " + id + " no longer exists.");
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
            UserProfile userProfile;
            try {
                userProfile = em.getReference(UserProfile.class, id);
                userProfile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userProfile with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Account accountOrphanCheck = userProfile.getAccount();
            if (accountOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UserProfile (" + userProfile + ") cannot be destroyed since the Account " + accountOrphanCheck + " in its account field has a non-nullable userProfile field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(userProfile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UserProfile> findUserProfileEntities() {
        return findUserProfileEntities(true, -1, -1);
    }

    public List<UserProfile> findUserProfileEntities(int maxResults, int firstResult) {
        return findUserProfileEntities(false, maxResults, firstResult);
    }

    private List<UserProfile> findUserProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UserProfile.class));
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

    public UserProfile findUserProfile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UserProfile.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UserProfile> rt = cq.from(UserProfile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
