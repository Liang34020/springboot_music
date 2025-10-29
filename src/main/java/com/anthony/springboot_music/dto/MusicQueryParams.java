package com.anthony.springboot_music.dto;

import com.anthony.springboot_music.constant.MusicCategory;

public class MusicQueryParams {

    private MusicCategory category;
    private String search;
    private String orderBy;
    private String sort;


    public MusicCategory getCategory() {
        return category;
    }

    public void setCategory(MusicCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
