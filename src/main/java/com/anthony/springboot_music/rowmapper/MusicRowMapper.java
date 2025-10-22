package com.anthony.springboot_music.rowmapper;

import com.anthony.springboot_music.constant.MusicSinger;
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

        String singerStr = resultSet.getString("singer");
        MusicSinger singer = MusicSinger.valueOf(singerStr);
        music.setSinger(singer);

//        music.setSinger(MusicSinger.valueOf(resultSet.getString("singer")));

        music.setYoutube_url(resultSet.getString("youtube_url"));
        music.setViews(resultSet.getInt("views"));
        music.setDescription(resultSet.getString("description"));
        music.setDuration(resultSet.getString("duration"));
        music.setCreated_date(resultSet.getDate("created_date"));
        music.setLast_modified_date(resultSet.getDate("last_modified_date"));

        return music;

    }
}
