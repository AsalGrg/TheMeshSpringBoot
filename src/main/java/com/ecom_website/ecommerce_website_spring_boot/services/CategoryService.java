package com.ecom_website.ecommerce_website_spring_boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ecom_website.ecommerce_website_spring_boot.entities.Category;
import com.ecom_website.ecommerce_website_spring_boot.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // for inserting new category
    public Category insertCategory(Category category) {
        Category categoryInserted = this.categoryRepository.save(category);
        return categoryInserted;
    }

    // for getting all the categories available
    public List<Category> getAllCats() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    public Category findByCategory_name(@Param("name") String category) {
        return this.categoryRepository.findByCategoryName(category);
    }

    public Category getCategoryById(int id) {
        return this.categoryRepository.findById(id).get();
    }

    public List<Category> allCatsExceptFor(String name) {
        return this.categoryRepository.allCatsExceptFor(name);
    }
}
