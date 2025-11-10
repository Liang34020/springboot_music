package com.anthony.springboot_music.dao.impl;


import com.anthony.springboot_music.dao.OrderDao;
import com.anthony.springboot_music.dto.CreateOrderRequest;
import com.anthony.springboot_music.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, LocalTime totalTime ){
        String sql = "INSERT INTO `order`(user_id, total_time, created_date, last_modified_date)" +
                "VALUES (:user_id, :total_time, :created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("total_time", totalTime);

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {


        String sql = "INSERT INTO order_item (order_id, music_id, time) VALUES (:order_id, :music_id, :time)";

        MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);

            mapSqlParameterSource[i] = new MapSqlParameterSource();
            mapSqlParameterSource[i].addValue("order_id", orderId);
            mapSqlParameterSource[i].addValue("music_id",orderItem.getMusic_id());
            mapSqlParameterSource[i].addValue("time", orderItem.getTime());
        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSource);


    }
}
