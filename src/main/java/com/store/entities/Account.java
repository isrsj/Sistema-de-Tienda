
package com.store.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table( name="Account" )
public class Account implements Serializable {
    
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    @Column( name="account_id" )
    private Integer id;
    
    @Column( name="nickname", unique=true, nullable=false )
    private String nickname;
    
    @Column( name="email", unique=true, nullable=false )
    private String email;
    
    @Column( name="account_password", nullable=false )
    private String password;
    
    @Column( name="registration_date", nullable=false )
    private LocalDateTime registration;
    
    @OneToOne( optional=false, fetch=FetchType.LAZY, cascade=CascadeType.ALL ) // FK
    @JoinColumn( name="profile_id", nullable=false )
    private UserProfile userProfile;
    
    @ManyToOne( optional=false, fetch=FetchType.LAZY ) // FK
    @JoinColumn( name="role_id", nullable=false )
    private AccountRole role;
    
    public Account() {}

    public Account(String nickname, String email, String password, LocalDateTime registration, UserProfile userProfile, AccountRole role) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.userProfile = userProfile;
        this.role = role;
    }

    public Account(Integer id, String nickname, String email, String password, LocalDateTime registration, UserProfile userProfile, AccountRole role) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.userProfile = userProfile;
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
       
}
