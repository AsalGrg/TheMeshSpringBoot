package com.ecom_website.ecommerce_website_spring_boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ecom_website.ecommerce_website_spring_boot.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

    @Query(value = "Select * from gender g where g.gender_name= :name ", nativeQuery = true)
    public Gender findByGender_name(@Param("name") String gender_name);

    @Query(value = "SELECT * FROM gender g WHERE g.gender_name<> :name", nativeQuery = true)
    public List<Gender> allGendersExceptFor(@Param("name") String gender_name);
}
