
package com.tienda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table( name="Cuenta" )
public class Cuenta {
    
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    @Column( name="ID_Cuenta" )
    private Integer id;
    
    @Column( name="nickname", unique=true, nullable=false )
    private String nickname;
    
    @Column( name="password", nullable=false )
    private String password;
    
    
    public Cuenta() {}

    public Cuenta(Integer id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    public Cuenta(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
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
