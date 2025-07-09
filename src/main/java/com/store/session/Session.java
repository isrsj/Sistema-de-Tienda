
package com.store.session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.function.Consumer;

/**
 *
 * @author Israel Reyes
 */
public class Session {
    
    public static void inSession ( EntityManager manager, Consumer<EntityManager> work ) {
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
