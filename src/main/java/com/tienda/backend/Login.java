
package com.tienda.backend;

import com.tienda.entities.Account;
import com.tienda.session.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
    
    private final int WIDTH = 150;
    private final int HEIGHT = 30;
    
    private EntityManagerFactory factory;
    private JTextField nickname, password;
    private JButton createAccount, login;
        
    private JPanel panel;
    
    public Login (JPanel panel, EntityManagerFactory factory) {
        this.panel = panel;
        this.factory = factory;
    }
    
    
    public void initPanelLogin() {
        nickname = new JTextField(30);
        password = new JTextField(30);
        login =  new JButton("Login");
        createAccount = new JButton ("Create an Account");
        
        nickname.setBounds(20, 20, WIDTH, HEIGHT);
        password.setBounds(20, 60, WIDTH, HEIGHT);
        login.setBounds(20, 100, WIDTH, HEIGHT);
        createAccount.setBounds(20, 140, WIDTH, HEIGHT);
        
        login.addActionListener( listenerLogin() );
        createAccount.addActionListener( listenerCreateAccount() );
        
        panel.add(nickname);
        panel.add(password);
        panel.add(login);
        panel.add(createAccount);
    }
    
    private ActionListener listenerCreateAccount () {
        return action -> {
            panel.removeAll();
            panel.revalidate();
            new SignIn(panel, factory).initSignIn();
            panel.repaint();
        };        
    }
    
    private ActionListener listenerLogin () {
        return action -> {
            String nick = nickname.getText();
            String pass = password.getText();

            if ( !nick.equals("") && !pass.equals("") ) {
                managerLogin( nick, pass );
            }
        };
    }
    
    private void managerLogin ( String nick, String pass ) {
        Session.inSession(factory, entityManager -> {

            validateAccount(entityManager, nick, pass);
            
        });
    }
    
    private void validateAccount (EntityManager entityManager, String nick, String pass) {
        
    }
    
    private void showMessage( String message ) {
        JOptionPane.showMessageDialog(null, message);
    }
        
}
