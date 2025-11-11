package com.anthony.springboot_music.dto;

public class OrderQueryQarams {

    private Integer userId;
    private Integer limti;
    private Integer offset;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLimti() {
        return limti;
    }

    public void setLimti(Integer limti) {
        this.limti = limti;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
