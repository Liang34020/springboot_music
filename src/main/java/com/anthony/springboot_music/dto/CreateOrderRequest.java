package com.anthony.springboot_music.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateOrderRequest {


    @NotEmpty
    private List<FavoriteItem> favoriteItemList;

    public List<FavoriteItem> getFavoriteItemList() {
        return favoriteItemList;
    }

    public void setFavoriteItemList(List<FavoriteItem> favoriteItemList) {
        this.favoriteItemList = favoriteItemList;
    }
}
