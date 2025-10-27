package com.anthony.springboot_music.dto;

import com.anthony.springboot_music.constant.MusicCategory;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;
import java.util.Date;


//為了與Music Class 區分使用 可能想要加上not null之類的
public class MusicRequest {

    @NonNull
    private String music_name;

    @NonNull
    private String singer;

    @NonNull
    private String category;

    @NonNull
    private String youtube_url;

    @NonNull
    private Integer views;

    @NonNull
    private String description;

    @NonNull
    private String duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    @NonNull
    private Date created_date;

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
