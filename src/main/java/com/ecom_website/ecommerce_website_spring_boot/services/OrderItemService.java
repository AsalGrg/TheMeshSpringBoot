package com.ecom_website.ecommerce_website_spring_boot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_website.ecommerce_website_spring_boot.entities.OrderItem;
import com.ecom_website.ecommerce_website_spring_boot.repositories.OrderItemRepository;

@Service
public class OrderItemService {

    @Autowired
    public OrderItemRepository itemRepository;

    public void addOrderToCart(OrderItem item) {
        this.itemRepository.save(item);
    }

    public List<OrderItem> getCartItemDetailsByUId(int user_id) {
        return this.itemRepository.getCartByUserId(user_id);
    }

    public List<OrderItem> getOrderItemDetailsByUId(int user_id) {
        return this.itemRepository.getOrderByUserId(user_id);
    }

    public void removeFromCarts(int product_id) {
        this.itemRepository.deleteById(product_id);
    }

    public OrderItem buyFromCart(int order_item_id) {
        OrderItem item = this.itemRepository.findById(order_item_id).get();
        item.setOrdered(true);
        return this.itemRepository.save(item);

    }

    public List<OrderItem> getOrderItemDetails() {
        return this.itemRepository.getOrders();
    }
}
