package com.anthony.springboot_music.dao.impl;

import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.dto.MusicRequest;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.rowmapper.MusicRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MusicDaoImpl implements MusicDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Music getMusicById(Integer musicId) {
        String sql = "SELECT music_id,music_name, singer, category, youtube_url, views, description, duration, created_date, last_modified_date FROM music WHERE music_id = :musicId";
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("musicId", musicId);
//        List<Music> musicList = namedParameterJdbcTemplate.query(sql, map, new MusicRowMapper());
//
        Map<String, Object> map = Collections.singletonMap("musicId", musicId);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, map, new MusicRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
//        if (!musicList.isEmpty()) {
//            return musicList.get(0);
//        } else {
//            return null;
//        }
    }

    @Override
    public Integer createMusic(MusicRequest musicRequest) {

        String sql = "INSERT INTO music(music_name, singer, category, youtube_url, views, description, duration, created_date, last_modified_date)" +
                "VALUES ( :music_name, :singer, :category, :youtube_url, :views, :description, :duration, :created_date, :last_modified_date)";

        Map<String, Object> map = new HashMap<>();
        map.put("music_name", musicRequest.getMusic_name());
        map.put("singer", musicRequest.getSinger());
        map.put("category", musicRequest.getCategory());
        map.put("youtube_url", musicRequest.getYoutube_url());
        map.put("views", musicRequest.getViews());
        map.put("description", musicRequest.getDescription());
        map.put("duration", musicRequest.getDuration());
        map.put("created_date", musicRequest.getCreated_date());

        Date now = new Date();
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder);

        int musicId = keyHolder.getKey().intValue();

        return musicId;
    }
}
