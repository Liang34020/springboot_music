package com.anthony.springboot_music.dao;

import com.anthony.springboot_music.dto.OrderQueryQarams;
import com.anthony.springboot_music.model.Order;
import com.anthony.springboot_music.model.OrderItem;

import java.time.LocalTime;
import java.util.List;

public interface OrderDao {

    Integer countOrders(OrderQueryQarams orderQueryQarams);

    List<Order> getOrders(OrderQueryQarams orderQueryQarams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemById(Integer orderId);

    Integer createOrder(Integer userId, LocalTime time);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
}
