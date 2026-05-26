package com.hotel.models;

import com.hotel.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "customers")
public class CustomerModel extends BaseModel {


    private String name;
    private Long phone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String idProofNumber;
    @Column(nullable = false)
    private Role role;

    private String password;

    public CustomerModel(String password,  String email, String username) {
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public CustomerModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Column(unique = true)
    private String username;

    public CustomerModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}