
package com.tienda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table( name="UserProfile" )
public class UserProfile {
    
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
    
    @Column( name="registration_date", nullable=false )
    private LocalDateTime registrationDate;
    
    public UserProfile() {}

    public UserProfile(String firstName, String paternalLastName, String maternalLastName, String phoneNumber, LocalDateTime registrationDate) {
        this.firstName = firstName;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
    }

    public UserProfile(Integer id, String firstName, String paternalLastName, String maternalLastName, String phoneNumber, LocalDateTime registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.paternalLastName = paternalLastName;
        this.maternalLastName = maternalLastName;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}
