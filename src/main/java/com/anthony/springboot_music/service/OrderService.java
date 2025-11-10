package com.anthony.springboot_music.service;

import com.anthony.springboot_music.dto.CreateOrderRequest;
import com.anthony.springboot_music.model.Order;
import com.anthony.springboot_music.model.OrderItem;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    
    Order getOrderById(Integer orderId);
}
