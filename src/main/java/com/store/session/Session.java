
package com.tienda.session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import static jakarta.persistence.Persistence.createEntityManagerFactory;
import java.util.function.Consumer;

/**
 *
 * @author Israel Reyes
 */
public class Session {
    
    public static EntityManagerFactory init( String persistenceUnit ) {
        return createEntityManagerFactory(persistenceUnit);
    }
    
    public static void inSession ( EntityManagerFactory factory, Consumer<EntityManager> work ) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        
        try {
            transaction.begin();
            work.accept(manager);
            transaction.commit();
        }
        catch ( Exception e ) {
            if ( transaction.isActive() ) transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
        
    }
    
}
