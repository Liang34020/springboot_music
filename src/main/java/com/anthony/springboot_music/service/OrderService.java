package com.anthony.springboot_music.service;

import com.anthony.springboot_music.dto.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
    

}
