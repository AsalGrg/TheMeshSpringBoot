package com.ecom_website.ecommerce_website_spring_boot.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom_website.ecommerce_website_spring_boot.dtos.ProductDTO;
import com.ecom_website.ecommerce_website_spring_boot.entities.Brand;
import com.ecom_website.ecommerce_website_spring_boot.entities.Category;
import com.ecom_website.ecommerce_website_spring_boot.entities.Gender;
import com.ecom_website.ecommerce_website_spring_boot.entities.Product;
import com.ecom_website.ecommerce_website_spring_boot.services.BrandService;
import com.ecom_website.ecommerce_website_spring_boot.services.CategoryService;
import com.ecom_website.ecommerce_website_spring_boot.services.GenderService;
import com.ecom_website.ecommerce_website_spring_boot.services.OrderItemService;
import com.ecom_website.ecommerce_website_spring_boot.services.ProductService;

@Controller

public class AdminController {

    public String commonPath = "/images/products/";
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private GenderService genderService;

    @Autowired
    private OrderItemService itemService;

    private String DIR_PATH = "/Users/mac/Documents/Spring and Spring Boot/Projects Spring/E-commerce Website Spring Boot/ecommerce_website_spring_boot/src/main/resources/static/images";

    @GetMapping("/admin/home")
    public String getHome() {
        return "admin/index";
    }

    @GetMapping("/admin/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", this.itemService.getOrderItemDetails());
        return "admin/orders";
    }

    @GetMapping("/admin/products")
    public String getProducts(Model model) {

        model.addAttribute("allProducts", this.productService.getAllProducts());

        return "admin/products";
    }

    // handler method to edit products
    @GetMapping("/admin/editProducts/{id}")
    public String editProducts(@PathVariable("id") int id, Model model) {
        Product product = this.productService.getProductsById(id);
        Category category = this.categoryService.getCategoryById(product.getCategory().getCat_id());
        Brand brand = this.brandService.getBrandById(product.getBrand().getBrand_id());
        Gender gender = this.genderService.getGenderById(product.getGender().getGender_id());

        // models
        model.addAttribute("productDetails", product);
        model.addAttribute("categoryName", category.getCat_name());
        model.addAttribute("brandName", brand.getBrand_name());
        model.addAttribute("genderName", gender.getGender_name());

        // models to get fetched as options
        model.addAttribute("categoriesOpt", this.categoryService.allCatsExceptFor(category.getCat_name()));
        model.addAttribute("brandsOpt", this.brandService.allBrandsExceptFor(brand.getBrand_name()));
        model.addAttribute("genderOpt", this.genderService.allGendersExceptFor(gender.getGender_name()));
        return "admin/editProducts";
    }

    // handler method for edit product process
    @PostMapping("admin/editProductProcess")
    public String editProductProcess(@ModelAttribute("updatedProduct") ProductDTO productDTO,
            @RequestParam("product-image1") MultipartFile img1,
            @RequestParam("product-image2") MultipartFile img2,
            @RequestParam("product-image3") MultipartFile img3) {

        Product product = new Product();
        product.setProduct_id(productDTO.getProduct_id());
        product.setProduct_name(productDTO.getProduct_name());
        product.setProduct_name(productDTO.getProduct_name());
        product.setProduct_price(productDTO.getProduct_price());
        product.setStock(productDTO.getStock());
        product.setProduct_desc(productDTO.getProduct_desc());
        product.setBrand(this.brandService.findByBrand_name(productDTO.getBrand_name()));
        product.setCategory(this.categoryService.findByCategory_name(productDTO.getCategory_name()));
        product.setGender(this.genderService.getByGenderName(productDTO.getGender_name()));

        System.out.println(img1.getOriginalFilename());

        try {

            if (img1.getSize() > 0) {

                product.setImg_path1(this.commonPath + img1.getOriginalFilename());

                byte[] data = img1.getBytes();
                FileOutputStream fos = new FileOutputStream(
                        DIR_PATH + "/products" + File.separator + img1.getOriginalFilename());
                fos.write(data);
                fos.close();
            }

            if (img2.getSize() > 0) {
                product.setImg_path1(this.commonPath + img2.getOriginalFilename());

                byte[] data = img2.getBytes();
                FileOutputStream fos = new FileOutputStream(
                        DIR_PATH + "/products" + File.separator + img1.getOriginalFilename());
                fos.write(data);
                fos.close();
            }

            if (img3.getSize() > 0) {
                product.setImg_path1(this.commonPath + img3.getOriginalFilename());

                byte[] data = img3.getBytes();
                FileOutputStream fos = new FileOutputStream(
                        DIR_PATH + "/products" + File.separator + img1.getOriginalFilename());
                fos.write(data);
                fos.close();
            }

            Product productInserted = this.productService.insertProduct(product);

            // checking for null values being inserted
            if (productInserted != null) {
                System.out.println("Product inserted successfully");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "redirect:/admin/products";
    }

    // handler method for deleting products
    @GetMapping("/admin/deleteProducts/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        this.productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    // handler method for getting add product page
    @GetMapping("/admin/addProducts")
    public String addProducts(Model model) {

        // sending all the available categories, brands and genders to fetch in the view
        model.addAttribute("categories", categoryService.getAllCats());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("genders", genderService.getAllGenders());

        return "admin/addProducts";

    }

    // handler method for inserting new products
    @PostMapping("/admin/addProductProcess")
    public String addProductProcess(@ModelAttribute("productDTO") ProductDTO productDTO,
            @RequestParam("product-image1") MultipartFile img1, @RequestParam("product-image2") MultipartFile img2,
            @RequestParam("product-image3") MultipartFile img3) {

        Product product = new Product();
        product.setProduct_name(productDTO.getProduct_name());
        product.setProduct_name(productDTO.getProduct_name());
        product.setProduct_price(productDTO.getProduct_price());
        product.setStock(productDTO.getStock());
        product.setProduct_desc(productDTO.getProduct_desc());
        product.setBrand(this.brandService.findByBrand_name(productDTO.getBrand_name()));
        product.setCategory(this.categoryService.findByCategory_name(productDTO.getCategory_name()));
        product.setGender(this.genderService.getByGenderName(productDTO.getGender_name()));

        System.out.println(img1.getOriginalFilename());

        try {

            if (img1.getSize() > 0) {

                product.setImg_path1(this.commonPath + img1.getOriginalFilename());

                byte[] data = img1.getBytes();
                FileOutputStream fos = new FileOutputStream(
                        DIR_PATH + "/products" + File.separator + img1.getOriginalFilename());
                fos.write(data);
                fos.close();
            }

            if (img2.getSize() > 0) {
                product.setImg_path1(this.commonPath + img2.getOriginalFilename());

                byte[] data = img2.getBytes();
                FileOutputStream fos = new FileOutputStream(
                        DIR_PATH + "/products" + File.separator + img1.getOriginalFilename());
                fos.write(data);
                fos.close();
            }

            if (img3.getSize() > 0) {
                product.setImg_path1(this.commonPath + img3.getOriginalFilename());

                byte[] data = img3.getBytes();
                FileOutputStream fos = new FileOutputStream(
                        DIR_PATH + "/products" + File.separator + img1.getOriginalFilename());
                fos.write(data);
                fos.close();
            }

            Product productInserted = this.productService.insertProduct(product);

            // checking for null values being inserted
            if (productInserted != null) {
                System.out.println("Product inserted successfully");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "admin/addProducts";
    }

    // // handler method to get all brands
    // @GetMapping("/admin/brands")
    // public String getBrands(Model model) {
    // List<Brand> brands = this.brandService.getAllBrands();
    // model.addAttribute("brands", brands);
    // return "admin/allBrands";
    // }

    // handler method to get add brands form
    @GetMapping("/admin/addBrands")
    public String addBrands() {
        return "admin/addBrands";
    }

    // handler method for adding the brands process
    @PostMapping("/admin/addBrandsProcess")
    public String addBrandsProcess(@ModelAttribute("brand") Brand brand,
            @RequestParam("brand_logo") MultipartFile file) {

        // image details
        brand.setBrand_image("/images/brands/" + file.getOriginalFilename());

        Brand insertedBrand = this.brandService.insertBrand(brand);

        if (insertedBrand != null) {
            System.out.println("Inserted Successfully");
        }

        // saving the image in the folder
        try {

            String path = DIR_PATH + File.separator + "brands" + File.separator + file.getOriginalFilename();
            byte[] data = file.getBytes();
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(data);
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "admin/addBrands";
    }

    @GetMapping("/admin/addCategories")
    public String addCats() {
        return "admin/addCategory";
    }

    // ---------------------------------------//

    // for inserting new categrories
    @PostMapping("/admin/addCategoryProcess")
    public String addCatsProcess(@ModelAttribute Category category) {
        Category insertedCat = this.categoryService.insertCategory(category);

        if (insertedCat != null) {
            System.out.println("Inserted Successfully");
        }
        return "admin/addCategory";
    }
}
