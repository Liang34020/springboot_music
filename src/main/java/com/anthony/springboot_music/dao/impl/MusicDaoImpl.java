package com.anthony.springboot_music.dao.impl;

import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.dao.MusicDao;
import com.anthony.springboot_music.dto.MusicQueryParams;
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
    public List<Music> getMusicList(MusicQueryParams musicQueryParams) {

        String sql = "SELECT * FROM music WHERE 1=1";

        Map<String, Object> map = new HashMap<>();
        MusicCategory category = musicQueryParams.getCategory();
        String search = musicQueryParams.getSearch();

        if (musicQueryParams.getCategory() != null) {
            sql += " AND category = :category";
            map.put("category", category.name());
        }

        if (search != null) {
            sql += " AND music_name LIKE :search";
            map .put("search", "%" + search + "%");
        }

        sql += " ORDER BY "+ musicQueryParams.getOrderBy() + " " + musicQueryParams.getSort();

        List<Music> musicList = namedParameterJdbcTemplate.query(sql, map, new MusicRowMapper());
        return musicList;
    }

    @Override
    public Music getMusicById(Integer musicId) {
        String sql = "SELECT music_id,music_name, singer, category, youtube_url, views, description, duration, created_date, last_modified_date FROM music WHERE music_id = :musicId";
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("musicId", musicId);
//        List<Music> musicList = namedParameterJdbcTemplate.query(sql, map, new MusicRowMapper());
//
//        if (!musicList.isEmpty()) {
//            return musicList.get(0);
//        } else {
//            return null;
//        }
        Map<String, Object> map = Collections.singletonMap("musicId", musicId);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, map, new MusicRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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

    @Override
    public void updateMusic(MusicRequest musicRequest){

        String sql = "UPDATE music SET music_name = :music_name, singer = :singer, category = :category, youtube_url = :youtube_url, views = :views, description = :description, duration = :duration, created_date = :created_date, last_modified_date = :last_modified_date WHERE music_id = :music_id";

        Map<String, Object> map = new HashMap<>();
        map.put("music_id", musicRequest.getMusic_id());
        map.put("music_name", musicRequest.getMusic_name());
        map.put("singer", musicRequest.getSinger());
        map.put("category", musicRequest.getCategory());
        map.put("youtube_url", musicRequest.getYoutube_url());
        map.put("views", musicRequest.getViews());
        map.put("description", musicRequest.getDescription());
        map.put("duration", musicRequest.getDuration());
        map.put("created_date", musicRequest.getCreated_date());
        map.put("last_modified_date", new Date());

        int rowsAffected = namedParameterJdbcTemplate.update(sql, map);

        // 如果想知道有沒有更新成功：(可省略
        if (rowsAffected > 0) {
            System.out.println("✅ 更新成功：" + rowsAffected + " 筆資料");
        } else {
            System.out.println("⚠️ 沒有找到符合條件的 music_id：" + musicRequest.getMusic_id());
        }
    }

    @Override
    public void deleteMusicById(Integer musicId) {

        String sql = "DELETE FROM music WHERE music_id = :music_id";

        Map<String, Object> map = new HashMap<>();
        map.put("music_id", musicId);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, map);

        // 如果想知道有沒有更新成功：(可省略
        if (rowsAffected > 0) {
            System.out.println("✅ 刪除成功：" + rowsAffected + " 筆資料");
        } else {
            System.out.println("⚠️ 沒有找到符合條件的 music_id：" + musicId);
        }
    }


}
