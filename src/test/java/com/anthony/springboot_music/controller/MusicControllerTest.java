package com.anthony.springboot_music.controller;

import com.anthony.springboot_music.constant.MusicCategory;
import com.anthony.springboot_music.dto.MusicRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getMusic_succes() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/music/{musicId}",1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.musicName", equalTo("月と私のかくれんぼ")))
                .andExpect(jsonPath("$.singer", equalTo("yutori")))
                .andExpect(jsonPath("$.musicCategory", equalTo("POP")))
                .andExpect(jsonPath("$.youtube_url", equalTo("https://www.youtube.com/watch?v=ldX1Ii0MofQ")))
                .andExpect(jsonPath("$.views", equalTo(494935)))
                .andExpect(jsonPath("$.description", equalTo("TVアニメ『ウィッチウォッチ』第２クールエンディングテーマ")))
                .andExpect(jsonPath("$.duration", equalTo("03:58")))
                .andExpect(jsonPath("$.created_date", equalTo("2025-07-13")))
                .andExpect(jsonPath("$.last_modified_date", equalTo("2025-10-21 05:15:00")));
    }

    @Test
    public void getMusic_notFound() throws Exception {
        RequestBuilder  requestBuilder = MockMvcRequestBuilders.get("/music/{musicId}",20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

//    創建
    @Transactional
    @Test
    public void createMusic_success() throws Exception {
        MusicRequest  musicRequest = new MusicRequest();
        musicRequest.setMusic_name("Test11");
        musicRequest.setSinger("Test11");
        musicRequest.setCategory(MusicCategory.TEST);
        musicRequest.setYoutube_url("Test11");
        musicRequest.setViews(0);
        musicRequest.setDescription("Test11");
        musicRequest.setDuration("03:58");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2025-10-31");
        musicRequest.setCreated_date(date);

        String json = objectMapper.writeValueAsString(musicRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/music")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.musicName", equalTo("Test11")))
                .andExpect(jsonPath("$.singer", equalTo("Test11")))
                .andExpect(jsonPath("$.musicCategory", equalTo("TEST")))
                .andExpect(jsonPath("$.youtube_url", equalTo("Test11")))
                .andExpect(jsonPath("$.views", equalTo(0)))
                .andExpect(jsonPath("$.description", equalTo("Test11")))
                .andExpect(jsonPath("$.duration", equalTo("03:58")))
                .andExpect(jsonPath("$.created_date", equalTo("2025-10-31")))
                .andExpect(jsonPath("$.last_modified_date", notNullValue()));

    }
    @Transactional
    @Test
    public void updateMusic_success() throws Exception {
        MusicRequest  musicRequest = new MusicRequest();
        musicRequest.setMusic_name("Test11");
        musicRequest.setSinger("Test11");
        musicRequest.setCategory(MusicCategory.TEST);
        musicRequest.setYoutube_url("Test11");
        musicRequest.setViews(0);
        musicRequest.setDescription("Test11");
        musicRequest.setDuration("03:58");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2025-10-31");
        musicRequest.setCreated_date(date);

        String json = objectMapper.writeValueAsString(musicRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/music/{musicId}",6)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.musicName", equalTo("Test11")))
                .andExpect(jsonPath("$.singer", equalTo("Test11")))
                .andExpect(jsonPath("$.musicCategory", equalTo("TEST")))
                .andExpect(jsonPath("$.youtube_url", equalTo("Test11")))
                .andExpect(jsonPath("$.views", equalTo(0)))
                .andExpect(jsonPath("$.description", equalTo("Test11")))
                .andExpect(jsonPath("$.duration", equalTo("03:58")))
                .andExpect(jsonPath("$.created_date", equalTo("2025-10-31")))
                .andExpect(jsonPath("$.last_modified_date", notNullValue()));
    }
    @Transactional
    @Test
    public void updataMusic_illegalArgument() throws Exception {
//        MusicRequest  musicRequest = new MusicRequest();
//        musicRequest.setMusic_name("Test11");

//        String json = objectMapper.writeValueAsString(musicRequest);

        String json = """
        {
            "music_name": "Test11"
        }
        """;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/music/{musicId}",6)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Transactional
    @Test
    public void updataMusic_musicNotFound() throws Exception {
        MusicRequest  musicRequest = new MusicRequest();
        musicRequest.setMusic_name("Test11");
        musicRequest.setSinger("Test11");
        musicRequest.setCategory(MusicCategory.TEST);
        musicRequest.setYoutube_url("Test11");
        musicRequest.setViews(0);
        musicRequest.setDescription("Test11");
        musicRequest.setDuration("03:58");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2025-10-31");
        musicRequest.setCreated_date(date);

        String json = objectMapper.writeValueAsString(musicRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/music/{music_id}",20000)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }
    @Transactional
    @Test
    public void deleteMusic_success() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/music/{musicId}",6);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    @Transactional
    @Test
    public void deleteMusic_deleteNotExistingMusic() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/music/{music_id}",20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Test
    public void getMusic() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/music");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(3)));
    }

    @Test
    public void getMusic_filtering() throws Exception {
        RequestBuilder  requestBuilder = MockMvcRequestBuilders
                .get("/music")
                .param("search", "Test")
                .param("category", "TEST");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(1)));
    }

    @Test
    public void getMusic_sorting() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/music")
                .param("orderBy", "views")
                .param("sort", "desc");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(3)))
                .andExpect(jsonPath("$.results[0].musicId", equalTo(2)))
                .andExpect(jsonPath("$.results[2].musicId", equalTo(6)));
    }

    @Test
    public void getMusic_pagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/music")
                .param("limit", "2")
                .param("offset", "2");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.limit", notNullValue()))
                .andExpect(jsonPath("$.offset", notNullValue()))
                .andExpect(jsonPath("$.total", notNullValue()))
                .andExpect(jsonPath("$.results", hasSize(1)))
                .andExpect(jsonPath("$.results[0].musicId", equalTo(6)));
    }
}