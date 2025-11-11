package com.anthony.springboot_music.controller;

import com.anthony.springboot_music.dto.CreateOrderRequest;
import com.anthony.springboot_music.dto.OrderQueryQarams;
import com.anthony.springboot_music.model.Order;
import com.anthony.springboot_music.service.OrderService;
import com.anthony.springboot_music.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        OrderQueryQarams orderQueryQarams = new OrderQueryQarams();
        orderQueryQarams.setUserId(userId);
        orderQueryQarams.setLimti(limit);
        orderQueryQarams.setOffset(offset);

//      取得 order list
        List<Order> orders = orderService.getOrders(orderQueryQarams);

//      取得 order 總數
        Integer count = orderService.countOrders(orderQueryQarams);

//      分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orders);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
