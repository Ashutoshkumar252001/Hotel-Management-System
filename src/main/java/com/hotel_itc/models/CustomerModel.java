package com.hotel_itc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "customers")
public class CustomerModel extends BaseModel {


   private String name;
   private String phone;
   private String email;
   private String id_proof;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_proof() {
        return id_proof;
    }

    public void setId_proof(String id_proof) {
        this.id_proof = id_proof;
    }
}
