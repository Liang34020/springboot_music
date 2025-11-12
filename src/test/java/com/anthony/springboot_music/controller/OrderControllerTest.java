package com.anthony.springboot_music.controller;

import com.anthony.springboot_music.dto.CreateOrderRequest;
import com.anthony.springboot_music.dto.FavoriteItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    //創建訂單
    @Transactional
    @Test
    public void createOrder_success() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        List<FavoriteItem> FavoriteItemList = new ArrayList<>();

        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setMusicId(36);
        FavoriteItemList.add(favoriteItem);

        createOrderRequest.setFavoriteItemList(FavoriteItemList);

        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders",36)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.orderId", notNullValue()))
                .andExpect(jsonPath("$.userId", equalTo(750)))
                .andExpect(jsonPath("$.totalAmount", equalTo(750)))
                .andExpect(jsonPath("$.orderItemList", hasSize(2)))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Transactional
    @Test
    public void createOrder_illegalArgument_emptyFavorite() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        List<FavoriteItem> FavoriteItemList = new ArrayList<>();
        createOrderRequest.setFavoriteItemList(FavoriteItemList);

        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders",36)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void createOrder_userNotExist() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        List<FavoriteItem> FavoriteItemList = new ArrayList<>();

        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setMusicId(36);
        FavoriteItemList.add(favoriteItem);

        createOrderRequest.setFavoriteItemList(FavoriteItemList);

        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders",100)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void createOrder_musicNotExist() throws Exception {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        List<FavoriteItem> FavoriteItemList = new ArrayList<>();

        FavoriteItem favoriteItem = new FavoriteItem();
        favoriteItem.setMusicId(36);
        FavoriteItemList.add(favoriteItem);

        createOrderRequest.setFavoriteItemList(FavoriteItemList);
        String json = objectMapper.writeValueAsString(createOrderRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{userId}/orders",36)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    public void getOrders() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders",36);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(2)))
                .andExpect(jsonPath("$.results[0].orderId", notNullValue()))
                .andExpect(jsonPath("$.results[0].userId", equalTo(1)))
                .andExpect(jsonPath("$.results[0].totalAmount", equalTo(100000)))
                .andExpect(jsonPath("$.results[0].orderItemList", hasSize(1)))
                .andExpect(jsonPath("$.results[0].createdDate", notNullValue()))
                .andExpect(jsonPath("$.results[0].lastModifiedDate", notNullValue()))
                .andExpect(jsonPath("$.results[1].orderId", notNullValue()))
                .andExpect(jsonPath("$.results[1].userId", equalTo(1)))
                .andExpect(jsonPath("$.results[1].totalAmount", equalTo(500690)))
                .andExpect(jsonPath("$.results[1].orderItemList", hasSize(3)))
                .andExpect(jsonPath("$.results[1].createdDate", notNullValue()))
                .andExpect(jsonPath("$.results[1].lastModifiedDate", notNullValue()));
    }

//     修改到這裡
    @Test
    public void getOrders_pagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 36)
                .param("limit", "2")
                .param("offset", "2");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(0)));
    }

    @Test
    public void getOrders_userHasNoOrder() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 2);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(0)));
    }

    @Test
    public void getOrders_userNotExist() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/{userId}/orders", 100);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(0)));
    }

}