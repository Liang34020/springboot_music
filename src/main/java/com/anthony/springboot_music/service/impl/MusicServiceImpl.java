package com.anthony.springboot_music.service.impl;

import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicDao musicDao;

    @Override
    public Music getMusicById(Integer musicId) {
        return musicDao.getMusicById(musicId);
    }
}
