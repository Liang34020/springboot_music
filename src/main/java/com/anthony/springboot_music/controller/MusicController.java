package com.anthony.springboot_music.controller;


import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusicController {

    @Autowired
    MusicService musicService;

//  搜尋
    @GetMapping("music/{musicId}")
    public ResponseEntity<Music> getMusicList(@PathVariable Integer musicId) {
        Music music = musicService.getMusicById(musicId);

        if (music != null) {
            return ResponseEntity.status(HttpStatus.OK).body(music);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

//  新增 (用post請求
    @PostMapping("/music")
    public ResponseEntity<Music> createMusic(@RequestBody @Valid MusicRequest musicRequest) {

        Integer musicId = musicService.createMusic(musicRequest);

        Music music = musicService.getMusicById(musicId);

        return ResponseEntity.status(HttpStatus.CREATED).body(music);

    }

}
