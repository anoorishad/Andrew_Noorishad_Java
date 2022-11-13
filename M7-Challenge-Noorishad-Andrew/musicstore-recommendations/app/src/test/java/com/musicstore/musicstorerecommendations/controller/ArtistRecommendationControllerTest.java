package com.musicstore.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorerecommendations.model.AlbumRecommendation;
import com.musicstore.musicstorerecommendations.model.ArtistRecommendation;
import com.musicstore.musicstorerecommendations.repository.AlbumRecommendationRepository;
import com.musicstore.musicstorerecommendations.repository.ArtistRecommendationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtistRecommendationRepository artistRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    ArtistRecommendation inputArtistRecommendation;
    ArtistRecommendation outputArtistRecommendation;
    ArtistRecommendation outputArtistRecommendation2;

    List<ArtistRecommendation> allArtistRecommendations;

    @Before
    public void setUp() throws Exception {
        inputArtistRecommendation = new ArtistRecommendation();
        inputArtistRecommendation.setArtistId(1);
        inputArtistRecommendation.setUserId(1);
        inputArtistRecommendation.setLiked(true);

        outputArtistRecommendation = new ArtistRecommendation();
        outputArtistRecommendation.setId(1);
        outputArtistRecommendation.setArtistId(1);
        outputArtistRecommendation.setUserId(1);
        outputArtistRecommendation.setLiked(true);


        outputArtistRecommendation2 = new ArtistRecommendation();
        outputArtistRecommendation2.setId(2);
        outputArtistRecommendation.setArtistId(1);
        outputArtistRecommendation.setUserId(1);
        outputArtistRecommendation.setLiked(false);

        allArtistRecommendations = new ArrayList<>(Arrays.asList(
                outputArtistRecommendation,
                outputArtistRecommendation2
        ));

        doReturn(outputArtistRecommendation).when(artistRecommendationRepository).save(inputArtistRecommendation);
        doReturn(Optional.of(outputArtistRecommendation)).when(artistRecommendationRepository).findById(1);
        doReturn(allArtistRecommendations).when(artistRecommendationRepository).findAll();
    }

    @Test
    public void shouldAddArtistRecommendationOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputArtistRecommendation);
        String outputJson = mapper.writeValueAsString(outputArtistRecommendation);

        mockMvc.perform(post("/artistrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetArtistRecommendationById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputArtistRecommendation);

        mockMvc.perform(get("/artistrecommendation/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllArtistRecommendations() throws Exception {
        String outputJson = mapper.writeValueAsString(allArtistRecommendations);

        mockMvc.perform(get("/artistrecommendation"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingAlbumRecommendation() throws Exception {
        inputArtistRecommendation.setId(1);
        inputArtistRecommendation.setLiked(false);

        String inputJson = mapper.writeValueAsString(inputArtistRecommendation);

        mockMvc.perform(put("/artistrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingAlbumRecommendation() throws Exception {
        mockMvc.perform(delete("/artistrecommendation/1"))
                .andExpect(status().isNoContent());
    }
}