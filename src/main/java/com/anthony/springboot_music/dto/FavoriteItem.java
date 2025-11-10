package com.anthony.springboot_music.dto;

import jakarta.validation.constraints.NotNull;

public class FavoriteItem {
    @NotNull
    private Integer musicId;

//    @NotNull
//    private Integer quantity;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
}
