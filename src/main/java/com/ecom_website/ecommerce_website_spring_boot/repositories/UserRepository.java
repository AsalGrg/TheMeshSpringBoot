package com.ecom_website.ecommerce_website_spring_boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom_website.ecommerce_website_spring_boot.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.email = :email AND u.password = :password", nativeQuery = true)
    public User getUserRegisterDetails(@Param("email") String email, @Param("password") String password);

}
