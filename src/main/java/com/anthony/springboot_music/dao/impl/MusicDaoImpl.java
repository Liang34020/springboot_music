package com.anthony.springboot_music.dao.impl;

import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.model.Music;
import com.anthony.springboot_music.rowmapper.MusicRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MusicDaoImpl implements MusicDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Music getMusicById(Integer musicId) {
        String sql = "SELECT music_id,music_name, singer, youtube_url, views, description, duration, created_date, last_modified_date FROM music WHERE music_id = :musicId";

        Map<String,Object> map = new HashMap<>();
        map.put("musicId", musicId);

        List<Music> musicList = namedParameterJdbcTemplate.query(sql, map, new MusicRowMapper());

        if (!musicList.isEmpty()) {
            return musicList.get(0);
        } else {
            return null;
        }
    }
}
