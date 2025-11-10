package com.anthony.springboot_music.service.impl;


import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.dao.OrderDao;
import com.anthony.springboot_music.dto.FavoriteItem;
import com.anthony.springboot_music.dto.CreateOrderRequest;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.model.OrderItem;
import com.anthony.springboot_music.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MusicDao musicDao;


    @Transactional //只要有作多個DAO層的呼叫 都要做這個處理 確保兩個呼叫都是成功才寫入
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

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
