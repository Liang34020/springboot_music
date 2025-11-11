package com.anthony.springboot_music.service.impl;


import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.dao.OrderDao;
import com.anthony.springboot_music.dao.UserDao;
import com.anthony.springboot_music.dto.FavoriteItem;
import com.anthony.springboot_music.dto.CreateOrderRequest;
import com.anthony.springboot_music.dto.OrderQueryQarams;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.model.Order;
import com.anthony.springboot_music.model.OrderItem;
import com.anthony.springboot_music.model.User;
import com.anthony.springboot_music.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MusicDao musicDao;

    @Autowired
    private UserDao userDao;


    @Override
    public Integer countOrders(OrderQueryQarams orderQueryQarams) {
        return orderDao.countOrders(orderQueryQarams);
    }

    @Override
    public List<Order> getOrders(OrderQueryQarams orderQueryQarams) {
        List<Order> orders = orderDao.getOrders(orderQueryQarams);

        for (Order order : orders) {
            List<OrderItem> orderItems = orderDao.getOrderItemById(order.getOrder_id());

            order.setOrderItemList(orderItems);
        }
        return orders;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemById(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional //只要有作多個DAO層的呼叫 都要做這個處理 確保兩個呼叫都是成功才寫入
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
//      檢查user是否存在
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn("該 userId={} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

//      設定時間格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//      總時長
        int total = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for (FavoriteItem favoriteItem : createOrderRequest.getFavoriteItemList()){
            Music music = musicDao.getMusicById(favoriteItem.getMusicId());

//          取得music時間 轉換為HH:mm:ss長度
            String duration = music.getDuration();
            if (duration.length() == 5){
                duration = "00:" + duration;
            }

//          計算時間總長
            LocalTime amount =  LocalTime.parse(duration, formatter);
            total = amount.toSecondOfDay() +total;

            OrderItem orderItem = new OrderItem();
            orderItem.setMusic_id(music.getMusicId());
            orderItem.setTime(LocalTime.parse(duration, formatter));

            orderItemList.add(orderItem);

        }

        LocalTime time = LocalTime.ofSecondOfDay(total);

        int orderId = orderDao.createOrder(userId, time);


        orderDao.createOrderItem(userId, orderItemList);

        return orderId;
    }
}
