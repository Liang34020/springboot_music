package com.anthony.springboot_music.controller;


import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicController {

    @Autowired
    MusicService musicService;

    @GetMapping("Music/{musicId}")
    public ResponseEntity<Music> getMusicList(@PathVariable Integer musicId) {
        Music music = musicService.getMusicById(musicId);

        if (music != null) {
            return ResponseEntity.status(HttpStatus.OK).body(music);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
