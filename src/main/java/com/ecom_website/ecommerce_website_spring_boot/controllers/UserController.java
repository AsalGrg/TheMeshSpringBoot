package com.ecom_website.ecommerce_website_spring_boot.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ecom_website.ecommerce_website_spring_boot.entities.OrderItem;
import com.ecom_website.ecommerce_website_spring_boot.entities.Product;
import com.ecom_website.ecommerce_website_spring_boot.entities.User;
import com.ecom_website.ecommerce_website_spring_boot.services.OrderItemService;
import com.ecom_website.ecommerce_website_spring_boot.services.ProductService;
import com.ecom_website.ecommerce_website_spring_boot.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller

public class UserController {
    private String DIR_PATH = "/Users/mac/Downloads/E-commerce Website Spring Boot/ecommerce_website_spring_boot/src/main/resources/static/images";
    private User user = null;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService itemService;

    @GetMapping("/home")
    public String getHome(Model model, HttpSession httpSession) {
        model.addAttribute("title", "Home");
        httpSession.setAttribute("user", user);
        httpSession.removeAttribute("loginFailedMsg");
        return "user/home";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("title", "All Products");
        return "user/products";
    }

    @GetMapping("/products/Men")
    public String getMenProducts(Model model) {
        List<Product> menProducts = this.productService.getProductsMen();
        model.addAttribute("products", menProducts);
        model.addAttribute("title", "Men Products");
        return "user/products";
    }

    @GetMapping("/products/Women")
    public String getWomenProducts(Model model) {
        List<Product> womenProducts = this.productService.getProductsWomen();
        model.addAttribute("title", "Products|Women");
        model.addAttribute("products", womenProducts);
        model.addAttribute("title", "Women Products");
        return "user/products";
    }

    @GetMapping("/products/search/{name}")
    public String getSearchProducts(@PathVariable("name") String name, Model model) {

        List<Product> searchProducts = this.productService.getSearchProducts(name);
        model.addAttribute("title", "Products|" + name);
        model.addAttribute("products", searchProducts);
        model.addAttribute("title", "Products for " + name);
        return "user/products";
    }

    @GetMapping("/productDescription/{id}")
    public String getProductDescription(@PathVariable("id") int product_id, Model model) {
        Product product = this.productService.getProductById(product_id);
        model.addAttribute("productDesc", product);
        return "user/productDesc";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, HttpSession httpSession) {
        model.addAttribute("title", "Login");
        return "user/login";
    }

    @PostMapping("/loginProcess")
    public String loginProcess(@ModelAttribute("loginDetails") User user, HttpSession httpSession) {
        User validUser = this.userService.authenticateUser(user.getEmail(), user.getPassword());
        if (validUser == null) {
            httpSession.setAttribute("loginFailedMsg", "Login Failed!! Please Try Again!");
            return "redirect:/login";
        }

        else {
            // initializzing the universal user variable with the validUser.
            this.user = validUser;

            // checking if the user is the admin
            if (validUser.getIs_admin() == 1) {
                return "redirect:/admin/home";
            }
        }

        return "redirect:/home";
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("title", "Signup");
        return "user/signup";
    }

    @PostMapping("/signupProcess")
    public String signupProcess(@Valid @ModelAttribute("user") User user,
            @RequestParam("user_image") MultipartFile file, BindingResult bindingResult)
            throws IOException {

        if (bindingResult.hasErrors()) {
            return "redirect:/signup";
        }

        if (file.isEmpty()) {
            user.setImage_user("default.jpg");
        } else {

            // image uploading
            user.setImage_user(file.getOriginalFilename());
            byte[] data = file.getBytes();
            FileOutputStream fos = new FileOutputStream(
                    DIR_PATH + "/users" + File.separator + file.getOriginalFilename());
            fos.write(data);
            fos.close();

        }

        // setting the role to user by default
        user.setIs_admin(2);

        User userSaved = this.userService.registerUser(user);

        System.out.println(user.getImage_user());

        if (userSaved != null) {
            System.out.println("User registered successfully");
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        this.user = null;
        return "redirect:/home";
    }

    @GetMapping("/addToCarts/{id}")
    public String addToCarts(@PathVariable("id") int id) {

        Product product = this.productService.getProductById(id);
        OrderItem item = new OrderItem();
        item.setQuantity(1);
        item.setPrice(product.getProduct_price());
        item.setGrandTotal(item.getQuantity() * item.getPrice());
        item.setProduct(product);
        item.setDate(LocalDate.now());
        item.setOrdered(false);
        item.setUser(this.user);

        this.itemService.addOrderToCart(item);
        return "redirect:/carts";
    }

    @GetMapping("/carts")
    public String goToCarts(Model model) {
        List<OrderItem> orders = this.itemService.getCartItemDetailsByUId(this.user.getUser_id());

        for (OrderItem orderItem : orders) {
            System.out.println(orderItem.getOrder_item_id());

        }
        model.addAttribute("title", "Carts");
        model.addAttribute("cartDetails", orders);
        return "user/carts";
    }

    @GetMapping("/removeItem/{id}")
    public String removeFromCarts(@PathVariable("id") int order_item_id) {
        this.itemService.removeFromCarts(order_item_id);
        return "redirect:/carts";
    }

    @GetMapping("/buyNow/{id}")
    public String buyFromCarts(@PathVariable("id") int order_item_id) {
        this.itemService.buyFromCart(order_item_id);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<OrderItem> orders = this.itemService.getOrderItemDetailsByUId(this.user.getUser_id());
        model.addAttribute("title", "Orders");
        model.addAttribute("orderDetails", orders);

        for (OrderItem orderItem : orders) {
            System.out.println(orderItem.getOrder_item_id());

        }

        return "user/orders";
    }

    @GetMapping("/profile")
    public String checkProfile(Model model) {
        model.addAttribute("title", "Profile");
        return "user/profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("updatedDetails") User user,
            @RequestParam("userPP") MultipartFile file, HttpSession httpSession) throws IOException {

        if (!file.isEmpty()) {
            user.setImage_user(file.getOriginalFilename());
            byte[] data = file.getBytes();
            FileOutputStream fos = new FileOutputStream(
                    this.DIR_PATH + File.separator + "users" + File.separator + file.getOriginalFilename());
            fos.write(data);
            fos.close();

        } else {
            user.setImage_user(this.user.getImage_user());
        }
        this.user = user;

        httpSession.setAttribute("user", user);

        this.userService.registerUser(user);
        // saving the updated file
        return "redirect:/profile";
    }

}
