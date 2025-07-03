
package com.tienda.interfaces;

import jakarta.persistence.EntityManagerFactory;
import javax.swing.JPanel;

public class SignIn {
    
    
    private JPanel panel;
    
    private EntityManagerFactory factory;
    
    public SignIn ( JPanel panel, EntityManagerFactory factory ) {
       this.panel = panel;
       this.factory = factory;
    }
    
    
    
}
