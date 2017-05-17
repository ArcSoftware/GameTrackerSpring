package com.theironyard.charlotte.entities;

import javax.persistence.*;

/**
 * Created by Jake on 5/10/17.
 */
@Entity //when booted up, this is something that needs to be added to the Database.
@Table(name = "users") //specify table name so we dont interfere with the "user" in postgress.
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true) //you cant not have more than one user with the same "name"- "constraint"
    private String name;

    @Column(nullable = false) //cant be false
    private String password;

//    private String passwordHash;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

//    public String getPasswordHash() {
//        return passwordHash;
//    }
}
