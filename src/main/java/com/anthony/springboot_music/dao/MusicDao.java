package com.anthony.springboot_music.dao;

import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;

public interface MusicDao {

    Music getMusicById(Integer musicId);

    Integer createMusic(MusicRequest musicRequest);

}



