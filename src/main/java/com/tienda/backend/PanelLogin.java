
package com.tienda.interfaces;
import jakarta.persistence.EntityManagerFactory;
import java.awt.Color;
import javax.swing.JPanel;

public class PanelLogin extends JPanel {
    
    private EntityManagerFactory factory;
    
    public PanelLogin ( EntityManagerFactory factory ) {
        this.factory = factory;
        
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        
        new Login( this, factory ).initPanelLogin();
        
    }
    
    
    
}
