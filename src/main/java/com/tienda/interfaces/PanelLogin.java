
package com.tienda.interfaces;

import com.tienda.entities.Cuenta;
import com.tienda.session.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelLogin extends JPanel implements KeyListener {
    
    private final int WIDTH = 150;
    private final int HEIGHT = 30;
    
    private EntityManagerFactory factory;
    private JTextField nickname, password;
    private JButton createAccount;
    
    public PanelLogin ( EntityManagerFactory factory ) {
        this.factory = factory;
        
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        
        nickname = new JTextField(30);
        password = new JTextField(30);
        createAccount = new JButton ("Create an Account");
        
        nickname.setBounds(20, 20, WIDTH, HEIGHT);
        password.setBounds(20, 60, WIDTH, HEIGHT);
        createAccount.setBounds(20, 100, WIDTH, HEIGHT);
        
        nickname.setFocusable(true);
        password.setFocusable(true);
        
        nickname.addKeyListener(this);
        password.addKeyListener(this);
        createAccount.addMouseListener( listenerCreateAccount() );
        
        add(nickname);
        add(password);
        add(createAccount);
        
    }
    
    private MouseAdapter listenerCreateAccount () {
        return new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                removeAll();
                repaint();
            }
        };        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
            String nick = nickname.getText();
            String pass = password.getText();
            
            if ( !nick.equals("") && !pass.equals("") ) {
                
                login( nick, pass );
                
            }
        }
        
    }
    
    private void login ( String nick, String pass ) {
        Session.inSession(factory, entityManager -> {

            validateAccount(entityManager, nick, pass);
            
        });
    }
    
    public void validateAccount (EntityManager entityManager, String nick, String pass) {
        String query = "where nickname=:paramNick";
        
        try {
            Cuenta cuenta = entityManager.createQuery(query, Cuenta.class)
                .setParameter("paramNick", nick)
                .getSingleResult();
            
            if ( cuenta.getPassword().equals(pass) ) {
                // llevar a otra interfaz
            } else {
                JOptionPane.showMessageDialog(null, "The password is incorrect");
            }
            
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en el sistema");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
