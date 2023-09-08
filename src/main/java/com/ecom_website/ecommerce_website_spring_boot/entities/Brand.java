package com.ecom_website.ecommerce_website_spring_boot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//creating entity of Brand
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int brand_id;

    private String brand_name;
    private String brand_image;

    public Brand() {
    }

    public Brand(int brand_id, String brand_name) {
        this.brand_id = brand_id;
        this.brand_name = brand_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_image() {
        return brand_image;
    }

    public void setBrand_image(String brand_image) {
        this.brand_image = brand_image;
    }

}
