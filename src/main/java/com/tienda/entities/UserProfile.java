
package com.tienda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table( name="UserProfile" )
public class UserProfile implements Serializable {
    
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    @Column( name="profile_id" )
    private Integer id;
    
    @Column( name="first_name", nullable=false )
    private String firstName;
    
    @Column( name="paternal_lastname", nullable=false )
    private String paternalLastName;
    
    @Column( name="maternal_lastname", nullable=true )
    private String maternalLastName;
    
    @Column( name="phone_number", nullable=false, unique=true )
    private String phoneNumber;
    
    @OneToOne( mappedBy="userProfile" )
    private Account account;
    
    public UserProfile() {}

    public UserProfile(String firstName, String paternalLastName, String maternalLastName, String phoneNumber, Account account) {
        this.firstName = firstName;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    public UserProfile(Integer id, String firstName, String paternalLastName, String maternalLastName, String phoneNumber, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.phoneNumber = phoneNumber;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPaternalLastName() {
        return paternalLastName;
    }

    public void setPaternalLastName(String paternalLastName) {
        this.paternalLastName = paternalLastName;
    }

    public String getMaternalLastName() {
        return maternalLastName;
    }

    public void setMaternalLastName(String maternalLastName) {
        this.maternalLastName = maternalLastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
