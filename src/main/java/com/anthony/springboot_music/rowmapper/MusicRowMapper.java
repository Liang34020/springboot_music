package com.anthony.springboot_music.rowmapper;

import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.model.Music;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicRowMapper implements RowMapper<Music> {

    @Override
    public Music mapRow(ResultSet resultSet, int i) throws SQLException{
        Music music = new Music();

        music.setMusicId(resultSet.getInt("music_id"));
        music.setMusicName(resultSet.getString("music_name"));
        music.setSinger(resultSet.getString("singer"));

//        String categoryStr = resultSet.getString("category");
//        MusicCategory category = MusicCategory.valueOf(categoryStr);
//        music.setCategory(category);
//                  ↓ ↓ ↓
        music.setCategory(MusicCategory.valueOf(resultSet.getString("category")));

        music.setYoutube_url(resultSet.getString("youtube_url"));
        music.setViews(resultSet.getInt("views"));
        music.setDescription(resultSet.getString("description"));
        music.setDuration(resultSet.getString("duration"));
        music.setCreated_date(resultSet.getDate("created_date"));
        music.setLast_modified_date(resultSet.getTimestamp("last_modified_date"));

        return music;

    }
}
