package com.anthony.springboot_music.controller;


import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.service.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//  修改 (Put
    @PutMapping("music/{musicId}")
    public ResponseEntity<Music> updateMusic(@PathVariable Integer musicId,
                                             @RequestBody MusicRequest musicRequest) {

        Music music = musicService.getMusicById(musicId);

        if (music != null) {
            // 把路徑的 id 設進物件裡
            musicRequest.setMusic_id(musicId);
            // 呼叫 Service 層
            musicService.updateMusic(musicRequest);
            Music updateMusic = musicService.getMusicById(musicId);
            return ResponseEntity.status(HttpStatus.OK).body(updateMusic);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("music/{musicId}")
    public ResponseEntity<Music> deleteMusicById(@PathVariable Integer musicId) {
        Music music = musicService.getMusicById(musicId);
        if (music != null) {
            musicService.deleteMusicById(musicId);
            return ResponseEntity.status(HttpStatus.OK).body(music);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
