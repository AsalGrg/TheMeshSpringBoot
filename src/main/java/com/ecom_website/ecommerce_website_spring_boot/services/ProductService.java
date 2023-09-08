package com.ecom_website.ecommerce_website_spring_boot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_website.ecommerce_website_spring_boot.entities.Product;
import com.ecom_website.ecommerce_website_spring_boot.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    // for getting all the products//
    public List<Product> getAllProducts() {
        List<Product> products = this.productRepo.findAll();
        return products;
    }

    // for inserting the product in the db//
    public Product insertProduct(Product product) {
        Product insertedProduct = this.productRepo.save(product);
        return insertedProduct;
    }

    // for getting the product by id//
    public Product getProductById(int id) {
        Optional<Product> products = this.productRepo.findById(id);
        return products.get();
    }

    public Product getProductsById(int id) {
        return this.productRepo.findById(id).get();
    }

    public void deleteProduct(int id) {
        this.productRepo.deleteById(id);
    }

    public List<Product> getProductsMen() {
        return this.productRepo.getProductsMen();

    }

    public List<Product> getProductsWomen() {
        return this.productRepo.getProductsWomen();

    }

    public List<Product> getSearchProducts(String name) {
        return this.productRepo.findProductsByBrandOrName(name);
    }

}
