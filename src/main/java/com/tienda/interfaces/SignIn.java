
package com.tienda.interfaces;

import jakarta.persistence.EntityManagerFactory;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignIn {
    
    private final int WIDTH = 150;
    private final int HEIGHT = 30;
    
    private JTextField fieldName, fieldMaternalName, fieldPaternalName, fieldEmail;
    private JTextField fieldNumberPhone, fieldNickname, fieldPassword;
    
    private JButton signIn;
    
    private JPanel panel;
    
    private EntityManagerFactory factory;
    
    public SignIn ( JPanel panel, EntityManagerFactory factory ) {
       this.panel = panel;
       this.factory = factory;
       
       
    }
    
    public void initSignIn() {
        fieldName = new JTextField(30);
        fieldPaternalName = new JTextField(30);
        fieldMaternalName = new JTextField(30);
        fieldNumberPhone = new JTextField(30);
        fieldNickname = new JTextField(30);
        fieldEmail = new JTextField(30);
        fieldPassword = new JTextField(30);
        signIn = new JButton("Sign In");
        
        fieldName.setBounds(20, 20, WIDTH, HEIGHT);
        fieldPaternalName.setBounds(20, 60, WIDTH, HEIGHT);
        fieldMaternalName.setBounds(20, 100, WIDTH, HEIGHT);
        fieldNumberPhone.setBounds(20, 140, WIDTH, HEIGHT);
        fieldNickname.setBounds(20, 180, WIDTH, HEIGHT);
        fieldEmail.setBounds(20, 220, WIDTH, HEIGHT);
        fieldPassword.setBounds(20, 260, WIDTH, HEIGHT);
        signIn.setBounds(20, 300, WIDTH, HEIGHT);
        
        signIn.addActionListener( listenerSignIn() );
        
        panel.add(fieldName);
        panel.add(fieldPaternalName);
        panel.add(fieldMaternalName);
        panel.add(fieldNumberPhone);
        panel.add(fieldNickname);
        panel.add(fieldEmail);
        panel.add(fieldPassword);
        panel.add(signIn);
    }
    
    private ActionListener listenerSignIn () {
        return action -> {
            String name = fieldName.getText();
            String paternalName = fieldPaternalName.getText();
            String maternalName = fieldMaternalName.getText();
            String numberPhone = fieldNumberPhone.getText();
            String nickname = fieldNickname.getText();
            String password = fieldPassword.getText();
            String email = fieldEmail.getText();
            
            if ( isValidForm( name, paternalName, maternalName, numberPhone, nickname, password, email) ) {
                
            } else {
                showMessage( "There are empty fields" );
            }
        };
    }
    
    private Boolean isValidForm (String name, String paternalName, String maternalName, String numberPhone, String nickname, String password, String email) {
        return !name.equals("") && !paternalName.equals("") && !maternalName.equals("") &&
                !numberPhone.equals("") && !nickname.equals("") && !password.equals("") && !email.equals("") ;
    }
    
    private void showMessage( String message ) {
        JOptionPane.showMessageDialog(null, message);
    }    
    
}
