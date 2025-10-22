package com.anthony.springboot_music.service;

import com.anthony.springboot_music.model.Music;

public interface MusicService {
    Music getMusicById(Integer musicId);
}
