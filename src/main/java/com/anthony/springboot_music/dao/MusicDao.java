package com.anthony.springboot_music.dao;

import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MusicDao {

    List<Music> getMusicList();

    Music getMusicById(Integer musicId);

    Integer createMusic(MusicRequest musicRequest);

    void updateMusic(MusicRequest musicRequest);

    void deleteMusicById(Integer musicId);


}



