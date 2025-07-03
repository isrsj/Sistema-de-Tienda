
package com.tienda.interfaces;
import jakarta.persistence.EntityManagerFactory;
import java.awt.Color;
import javax.swing.JPanel;

public class PanelLogin extends JPanel {
    
    private EntityManagerFactory factory;
    
    private Login login;
    
    public PanelLogin ( EntityManagerFactory factory ) {
        this.factory = factory;
        
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        
        login = new Login( this, factory );
        login.initPanelLogin();
        
    }
    
    
    
}
