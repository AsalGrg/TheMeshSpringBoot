package com.ecom_website.ecommerce_website_spring_boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom_website.ecommerce_website_spring_boot.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product p WHERE p.gender_id= 1", nativeQuery = true)
    public List<Product> getProductsMen();

    @Query(value = "SELECT * FROM product p WHERE p.gender_id= 2", nativeQuery = true)
    public List<Product> getProductsWomen();

    @Query("SELECT p FROM Product p JOIN p.brand b WHERE b.brand_name LIKE %:search% OR p.product_name LIKE %:search%")
    List<Product> findProductsByBrandOrName(@Param("search") String search);
}
