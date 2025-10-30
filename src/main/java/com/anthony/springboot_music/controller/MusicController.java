package com.anthony.springboot_music.controller;


import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.dto.MusicQueryParams;
import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.service.MusicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class MusicController {

    @Autowired
    MusicService musicService;

    @GetMapping("music")
    public ResponseEntity<List<Music>> getMusicList(
//          查詢條件 Filtering
            @RequestParam(required = false) MusicCategory category,
            @RequestParam(required = false) String search,

//          排序 sorting
            @RequestParam(defaultValue = "views") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

//          分頁 Pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        MusicQueryParams musicQueryParams = new MusicQueryParams();
        musicQueryParams.setCategory(category);
        musicQueryParams.setSearch(search);
        musicQueryParams.setOrderBy(orderBy);
        musicQueryParams.setSort(sort);
        musicQueryParams.setLimit(limit);
        musicQueryParams.setOffset(offset);



        List<Music> musicList = musicService.getMusicList(musicQueryParams);

        return  ResponseEntity.status(HttpStatus.OK).body(musicList);
    }

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
