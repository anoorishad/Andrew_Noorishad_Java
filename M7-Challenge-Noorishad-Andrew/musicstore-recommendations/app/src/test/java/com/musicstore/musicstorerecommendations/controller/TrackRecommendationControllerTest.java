package com.musicstore.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorerecommendations.model.TrackRecommendation;
import com.musicstore.musicstorerecommendations.repository.TrackRecommendationRepository;
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
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrackRecommendationRepository trackRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    TrackRecommendation inputTrackRecommendation;
    TrackRecommendation outputTrackRecommendation;
    TrackRecommendation outputTrackRecommendation2;

    List<TrackRecommendation> allTrackRecommendations;

    @Before
    public void setUp() throws Exception {
        inputTrackRecommendation = new TrackRecommendation();
        inputTrackRecommendation.setTrackId(1);
        inputTrackRecommendation.setUserId(1);
        inputTrackRecommendation.setLiked(true);

        outputTrackRecommendation = new TrackRecommendation();
        outputTrackRecommendation.setId(1);
        outputTrackRecommendation.setTrackId(1);
        outputTrackRecommendation.setUserId(1);
        outputTrackRecommendation.setLiked(true);


        outputTrackRecommendation2 = new TrackRecommendation();
        outputTrackRecommendation2.setId(2);
        outputTrackRecommendation.setTrackId(1);
        outputTrackRecommendation.setUserId(1);
        outputTrackRecommendation.setLiked(false);

        allTrackRecommendations = new ArrayList<>(Arrays.asList(
                outputTrackRecommendation,
                outputTrackRecommendation2
        ));

        doReturn(outputTrackRecommendation).when(trackRecommendationRepository).save(inputTrackRecommendation);
        doReturn(Optional.of(outputTrackRecommendation)).when(trackRecommendationRepository).findById(1);
        doReturn(allTrackRecommendations).when(trackRecommendationRepository).findAll();
    }

    @Test
    public void shouldAddTrackRecommendationOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputTrackRecommendation);
        String outputJson = mapper.writeValueAsString(outputTrackRecommendation);

        mockMvc.perform(post("/trackrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTrackRecommendationById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTrackRecommendation);

        mockMvc.perform(get("/trackrecommendation/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllTrackRecommendations() throws Exception {
        String outputJson = mapper.writeValueAsString(allTrackRecommendations);

        mockMvc.perform(get("/trackrecommendation"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingTrackRecommendation() throws Exception {
        inputTrackRecommendation.setId(1);
        inputTrackRecommendation.setLiked(false);

        String inputJson = mapper.writeValueAsString(inputTrackRecommendation);

        mockMvc.perform(put("/trackrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingTrackRecommendation() throws Exception {
        mockMvc.perform(delete("/trackrecommendation/1"))
                .andExpect(status().isNoContent());
    }
}