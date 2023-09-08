package com.ecom_website.ecommerce_website_spring_boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_website.ecommerce_website_spring_boot.entities.Gender;
import com.ecom_website.ecommerce_website_spring_boot.repositories.GenderRepository;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderRepository;

    public List<Gender> getAllGenders() {
        List<Gender> genders = this.genderRepository.findAll();
        return genders;
    }

    public Gender getByGenderName(String gender) {
        return this.genderRepository.findByGender_name(gender);
    }

    public Gender getGenderById(int id) {
        return this.genderRepository.findById(id).get();
    }

    public List<Gender> allGendersExceptFor(String name) {
        return this.genderRepository.allGendersExceptFor(name);
    }

}
