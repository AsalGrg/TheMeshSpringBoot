package com.ecom_website.ecommerce_website_spring_boot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecom_website.ecommerce_website_spring_boot.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = "SELECT * from order_item o where o.user_id =:user_id AND o.is_ordered= 0", nativeQuery = true)
    public List<OrderItem> getCartByUserId(@Param("user_id") int userId);

    @Query(value = "SELECT * from order_item o where o.user_id =:user_id AND o.is_ordered= true", nativeQuery = true)
    public List<OrderItem> getOrderByUserId(@Param("user_id") int userId);

    @Query(value = "SELECT * from order_item o where o.is_ordered= true", nativeQuery = true)
    public List<OrderItem> getOrders();
}
