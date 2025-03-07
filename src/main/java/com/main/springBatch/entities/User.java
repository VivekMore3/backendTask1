package com.main.springBatch.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @Column
    private String FirstName;
    @Column
    private String lastname;
    @Column
    private String emailId;
    @Column
    private long phoneNumber;
    @Column
    private String password;

    public  User (){}

    public User(String firstName, String lastname, String emailId, long phoneNumber, String password) {
        FirstName = firstName;
        this.lastname = lastname;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
