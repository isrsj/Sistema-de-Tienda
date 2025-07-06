
package com.tienda.interfaces;

import com.tienda.entities.Account;
import com.tienda.entities.AccountRole;
import com.tienda.entities.UserProfile;
import com.tienda.session.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
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
            String phoneNumber = fieldNumberPhone.getText();
            String nickname = fieldNickname.getText();
            String password = fieldPassword.getText();
            String email = fieldEmail.getText();
            
            if ( isValidForm( name, paternalName, maternalName, phoneNumber, nickname, password, email) ) {
                managerLogin( name, paternalName, maternalName, phoneNumber, nickname, password, email);
            } else {
                showMessage( "There are empty fields" );
            }
        };
    }
        
    private Boolean isValidForm (String name, String paternalName, String maternalName, String phoneNumber, String nickname, String password, String email) {
        return !name.equals("") && !paternalName.equals("") && !maternalName.equals("") &&
                !phoneNumber.equals("") && !nickname.equals("") && !password.equals("") && !email.equals("") ;
    }
    
    private void managerLogin (String name, String paternalName, String maternalName, String phoneNumber, String nickname, String password, String email) {
        Session.inSession(factory, entityManager -> {

            UserProfile profile = createUserProfile(name, paternalName, maternalName, phoneNumber);
            
            AccountRole role = searchRole(entityManager);
            
            Account account = createAccount(nickname, email, password, profile, role);
            
            role.setSingleAccount(account);
            profile.setAccount(account);
            
            entityManager.persist(account);
            
        });
    }
    
    private UserProfile createUserProfile(String name, String paternalName, String maternalName, String phoneNumber ) {
        UserProfile profile = new UserProfile();
        profile.setFirstName(name);
        profile.setPaternalLastName(paternalName);
        profile.setMaternalLastName(maternalName);
        profile.setPhoneNumber(phoneNumber);
        return profile;
    }
    
    private Account createAccount(String nickname, String email, String password, UserProfile profile, AccountRole role) {
        Account account = new Account();
        account.setNickname(nickname);
        account.setEmail(email);
        account.setPassword(password);
        account.setUserProfile(profile);
        account.setRole(role);
        account.setRegistration(LocalDateTime.now());
        return account;
    }
    
    private AccountRole searchRole ( EntityManager entity ) {
        String hql = "where name = 'Employee'";
        try {
            return entity.createQuery( hql, AccountRole.class ).getSingleResult();
        } catch ( NoResultException e ) {
            showMessage("That role was not found");
            return null;
        }
    }
    
    private void showMessage( String message ) {
        JOptionPane.showMessageDialog(null, message);
    }    
    
}
