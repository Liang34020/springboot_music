package com.anthony.springboot_music.dao;

import com.anthony.springboot_music.model.Order;
import com.anthony.springboot_music.model.OrderItem;

import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, LocalTime time);

    void createOrderItem(Integer orderId, List<OrderItem> orderItemList);




}
