package com.ecom_website.ecommerce_website_spring_boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom_website.ecommerce_website_spring_boot.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "Select * from category c where c.cat_name= :name ", nativeQuery = true)
    public Category findByCategoryName(@Param("name") String cat_name);

    @Query(value = "SELECT * FROM category c WHERE c.cat_name<> :name", nativeQuery = true)
    public List<Category> allCatsExceptFor(@Param("name") String cat_name);
}
