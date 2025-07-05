
package com.tienda.interfaces;

import jakarta.persistence.EntityManagerFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignIn {
    
    private final int WIDTH = 150;
    private final int HEIGHT = 30;
    
    private JTextField fieldName, fieldMaternalName, fieldPaternalName;
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
        fieldPassword = new JTextField(30);
        signIn = new JButton("Sign In");
        
        fieldName.setBounds(20, 20, WIDTH, HEIGHT);
        fieldPaternalName.setBounds(20, 60, WIDTH, HEIGHT);
        fieldMaternalName.setBounds(20, 100, WIDTH, HEIGHT);
        fieldNumberPhone.setBounds(20, 140, WIDTH, HEIGHT);
        fieldNickname.setBounds(20, 180, WIDTH, HEIGHT);
        fieldPassword.setBounds(20, 220, WIDTH, HEIGHT);
        signIn.setBounds(20, 260, WIDTH, HEIGHT);
        
        panel.add(fieldName);
        panel.add(fieldPaternalName);
        panel.add(fieldMaternalName);
        panel.add(fieldNumberPhone);
        panel.add(fieldNickname);
        panel.add(fieldPassword);
        panel.add(signIn);
    }
    
    
    
}
