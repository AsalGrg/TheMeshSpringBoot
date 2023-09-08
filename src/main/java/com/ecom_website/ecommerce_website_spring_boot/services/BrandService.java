package com.ecom_website.ecommerce_website_spring_boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_website.ecommerce_website_spring_boot.entities.Brand;
import com.ecom_website.ecommerce_website_spring_boot.repositories.BrandRepository;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand insertBrand(Brand brand) {
        Brand insertedBrand = this.brandRepository.save(brand);
        return insertedBrand;
    }

    public List<Brand> getAllBrands() {
        List<Brand> brands = this.brandRepository.findAll();
        return brands;
    }

    public Brand findByBrand_name(String name) {
        return this.brandRepository.findByBrand_name(name);
    }

    public Brand getBrandById(int id) {
        return this.brandRepository.findById(id).get();
    }

    public List<Brand> allBrandsExceptFor(String name) {
        return this.brandRepository.allBrandsExceptFor(name);
    }
}
