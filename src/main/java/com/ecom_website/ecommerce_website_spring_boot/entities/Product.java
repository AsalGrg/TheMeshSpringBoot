package com.ecom_website.ecommerce_website_spring_boot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int product_id;

    private String product_name;
    private double product_price;
    private String img_path1;
    private String img_path2;
    private String img_path3;
    private int stock;
    private String product_desc;

    // all the foreign attributes

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    private Gender gender;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getImg_path1() {
        return img_path1;
    }

    public void setImg_path1(String img_path1) {
        this.img_path1 = img_path1;
    }

    public String getImg_path2() {
        return img_path2;
    }

    public void setImg_path2(String img_path2) {
        this.img_path2 = img_path2;
    }

    public String getImg_path3() {
        return img_path3;
    }

    public void setImg_path3(String img_path3) {
        this.img_path3 = img_path3;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
