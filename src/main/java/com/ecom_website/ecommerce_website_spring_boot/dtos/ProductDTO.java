package com.ecom_website.ecommerce_website_spring_boot.dtos;

public class ProductDTO {
    private int product_id;

    private String product_name;
    private double product_price;
    private String img_path1;
    private String img_path2;
    private String img_path3;
    private int stock;
    private String product_desc;

    // all the foreign attributes

    private String category_name;
    private String brand_name;
    private String gender_name;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

}
