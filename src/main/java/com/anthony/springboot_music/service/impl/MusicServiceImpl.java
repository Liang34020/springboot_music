package com.anthony.springboot_music.service.impl;

import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.dto.MusicQueryParams;
import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicDao musicDao;

    @Override
    public List<Music> getMusicList(MusicQueryParams musicQueryParams) {
        return musicDao.getMusicList(musicQueryParams);
    }

    @Override
    public Music getMusicById(Integer musicId) {
        return musicDao.getMusicById(musicId);
    }

    @Override
    public Integer createMusic(MusicRequest musicRequest) {
        return musicDao.createMusic(musicRequest);
    }

    @Override
    public void updateMusic(MusicRequest musicRequest) {
        musicDao.updateMusic(musicRequest);
    }

    @Override
    public void deleteMusicById(Integer musicId) {
        musicDao.deleteMusicById(musicId);
    }
}
