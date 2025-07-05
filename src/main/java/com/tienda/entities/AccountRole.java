
package com.tienda.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name="AccountRole" )
public class AccountRole {
    
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    @Column( name="role_id" )
    private Integer id;
    
    @Column( name="role_name", nullable=false, unique=true )
    private String name;
    
    public AccountRole () {}

    public AccountRole(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
