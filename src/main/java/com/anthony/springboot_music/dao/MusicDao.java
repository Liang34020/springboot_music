package com.anthony.springboot_music.dao;

import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.dto.MusicQueryParams;
import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MusicDao {

    Integer countMusic(MusicQueryParams musicQueryParams);

    List<Music> getMusicList(MusicQueryParams musicQueryParams);

    Music getMusicById(Integer musicId);

    Integer createMusic(MusicRequest musicRequest);

    void updateMusic(MusicRequest musicRequest);

    void deleteMusicById(Integer musicId);


}



