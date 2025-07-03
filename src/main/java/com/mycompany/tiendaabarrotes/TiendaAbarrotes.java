
package com.mycompany.tiendaabarrotes;

import com.tienda.interfaces.PanelLogin;
import com.tienda.session.Session;
import jakarta.persistence.EntityManagerFactory;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Israel Reyes
 */
public class TiendaAbarrotes extends JFrame {

    public final int WIDTH_FRAME = 1000;
    public final int HEIGHT_FRAME = 700;
    
    public TiendaAbarrotes ( JPanel panel ) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setPreferredSize( new Dimension( WIDTH_FRAME, HEIGHT_FRAME ) );
        add(panel);
        
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        
        EntityManagerFactory factory = Session.init("tienda_persistence_unit");
        
        new TiendaAbarrotes( new PanelLogin(factory) );
        
    }
    
    
}
