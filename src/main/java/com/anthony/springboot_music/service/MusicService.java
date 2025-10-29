package com.anthony.springboot_music.service;

import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;

import java.util.List;

public interface MusicService {

    List<Music> getMusicList(MusicCategory categorym, String search);

    Music getMusicById(Integer musicId);

    Integer createMusic(MusicRequest musicRequest);

    void updateMusic(MusicRequest musicRequest);

    void deleteMusicById(Integer musicId);
}

