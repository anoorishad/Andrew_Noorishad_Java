package com.musicstore.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorerecommendations.model.AlbumRecommendation;
import com.musicstore.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlbumRecommendationRepository albumRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    AlbumRecommendation inputAlbumRecommendation;
    AlbumRecommendation outputAlbumRecommendation;
    AlbumRecommendation outputAlbumRecommendation2;

    List<AlbumRecommendation> allAlbumRecommendations;

    @Before
    public void setUp() throws Exception {
        inputAlbumRecommendation = new AlbumRecommendation();
        inputAlbumRecommendation.setAlbumId(1);
        inputAlbumRecommendation.setUserId(1);
        inputAlbumRecommendation.setLiked(true);

        outputAlbumRecommendation = new AlbumRecommendation();
        outputAlbumRecommendation.setId(1);
        outputAlbumRecommendation.setAlbumId(1);
        outputAlbumRecommendation.setUserId(1);
        outputAlbumRecommendation.setLiked(true);


        outputAlbumRecommendation2 = new AlbumRecommendation();
        outputAlbumRecommendation2.setId(2);
        outputAlbumRecommendation.setAlbumId(1);
        outputAlbumRecommendation.setUserId(1);
        outputAlbumRecommendation.setLiked(false);

        allAlbumRecommendations = new ArrayList<>(Arrays.asList(
                outputAlbumRecommendation,
                outputAlbumRecommendation2
        ));

        doReturn(outputAlbumRecommendation).when(albumRecommendationRepository).save(inputAlbumRecommendation);
        doReturn(Optional.of(outputAlbumRecommendation)).when(albumRecommendationRepository).findById(1);
        doReturn(allAlbumRecommendations).when(albumRecommendationRepository).findAll();
    }

    @Test
    public void shouldAddAlbumRecommendationOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputAlbumRecommendation);
        String outputJson = mapper.writeValueAsString(outputAlbumRecommendation);

        mockMvc.perform(post("/albumrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAlbumRecommendationById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAlbumRecommendation);

        mockMvc.perform(get("/albumrecommendation/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllAlbumRecommendations() throws Exception {
        String outputJson = mapper.writeValueAsString(allAlbumRecommendations);

        mockMvc.perform(get("/albumrecommendation"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingAlbumRecommendation() throws Exception {
        inputAlbumRecommendation.setId(1);
        inputAlbumRecommendation.setLiked(false);

        String inputJson = mapper.writeValueAsString(inputAlbumRecommendation);

        mockMvc.perform(put("/albumrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingAlbumRecommendation() throws Exception {
        mockMvc.perform(delete("/albumrecommendation/1"))
                .andExpect(status().isNoContent());
    }
}