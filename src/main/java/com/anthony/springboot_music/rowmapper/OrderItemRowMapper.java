package com.anthony.springboot_music.rowmapper;

import com.anthony.springboot_music.model.OrderItem;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {

    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException{
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder_item_id(resultSet.getInt("order_item_id"));
        orderItem.setOrder_id(resultSet.getInt("order_id"));
        orderItem.setMusic_id(resultSet.getInt("music_id"));
        orderItem.setTime(resultSet.getTime("time").toLocalTime());

        orderItem.setMusic_name(resultSet.getString("music_name"));
        orderItem.setMusic_url(resultSet.getString("music_url"));

        return orderItem;


    }

}
