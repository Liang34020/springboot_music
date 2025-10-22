package com.anthony.springboot_music.model;

import com.anthony.springboot_music.constant.MusicSinger;

import java.util.Date;

public class Music {

    private Integer music_id;
    private String music_name;
    private MusicSinger singer;
    private String youtube_url;
    private Integer views;
    private String description;
    private String duration;
    private Date created_date;
    private Date last_modified_date;

    public Integer getMusicId() {
        return music_id;
    }

    public void setMusicId(Integer musicId) {
        this.music_id = musicId;
    }

    public String getMusicName() {
        return music_name;
    }

    public void setMusicName(String musicName) {
        this.music_name = musicName;
    }

    public MusicSinger getSinger() {
        return singer;
    }

    public void setSinger(MusicSinger singer) {
        this.singer = singer;
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

    public Date getLast_modified_date() {
        return last_modified_date;
    }

    public void setLast_modified_date(Date last_modified_date) {
        this.last_modified_date = last_modified_date;
    }
}
