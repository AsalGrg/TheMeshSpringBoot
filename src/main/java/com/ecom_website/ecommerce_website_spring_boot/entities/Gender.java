package com.ecom_website.ecommerce_website_spring_boot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gender_id;

    private String gender_name;

    public int getGender_id() {
        return gender_id;
    }

    public void setGender_id(int gender_id) {
        this.gender_id = gender_id;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

}
