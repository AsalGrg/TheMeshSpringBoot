package com.ecom_website.ecommerce_website_spring_boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom_website.ecommerce_website_spring_boot.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query(value = "Select * from brand b where b.brand_name= :name ", nativeQuery = true)
    public Brand findByBrand_name(@Param("name") String brand_name);

    @Query(value = "SELECT * FROM brand b WHERE b.brand_name<> :name", nativeQuery = true)
    public List<Brand> allBrandsExceptFor(@Param("name") String brand_name);
}
