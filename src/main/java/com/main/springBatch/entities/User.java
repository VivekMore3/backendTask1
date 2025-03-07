package com.main.springBatch.entities;


import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName; // Changed from FirstName to firstName
    @Column
    private String lastname;
    @Column
    private String emailId;
    @Column
    private long phoneNumber;
    @Column
    private String password;

    public User(){}

    public User(int id, String firstName, String lastname, String emailId, long phoneNumber, String password) {
        this.id = id;
        this.firstName = firstName; // Updated to use this.firstName
        this.lastname = lastname;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() { // Updated getter
        return firstName;
    }

    public void setFirstName(String firstName) { // Updated setter
        this.firstName = firstName;
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
