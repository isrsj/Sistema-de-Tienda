
package com.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name="AccountRole" )
public class AccountRole implements Serializable {
    
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    @Column( name="role_id" )
    private Integer id;
    
    @Column( name="role_name", nullable=false, unique=true )
    private String name;
    
    @OneToMany( mappedBy="role" )
    private Set<Account> account;
    
    public AccountRole () {}

    public AccountRole(String name) {
        this.name = name;
    }

    public AccountRole(Integer id, String name, Set<Account> account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
    }
    
    public void setSingleAccount(Account account) {
        this.account.add(account);
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
