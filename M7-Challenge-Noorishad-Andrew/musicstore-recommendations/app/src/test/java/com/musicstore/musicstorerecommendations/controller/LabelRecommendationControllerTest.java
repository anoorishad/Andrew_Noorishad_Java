package com.musicstore.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorerecommendations.model.LabelRecommendation;
import com.musicstore.musicstorerecommendations.repository.AlbumRecommendationRepository;
import com.musicstore.musicstorerecommendations.repository.LabelRecommendationRepository;
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
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LabelRecommendationRepository labelRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    LabelRecommendation inputLabelRecommendation;
    LabelRecommendation outputLabelRecommendation;
    LabelRecommendation outputLabelRecommendation2;

    List<LabelRecommendation> allLabelRecommendations;

    @Before
    public void setUp() throws Exception {
        inputLabelRecommendation = new LabelRecommendation();
        inputLabelRecommendation.setLabelId(1);
        inputLabelRecommendation.setUserId(1);
        inputLabelRecommendation.setLiked(true);

        outputLabelRecommendation = new LabelRecommendation();
        outputLabelRecommendation.setId(1);
        outputLabelRecommendation.setLabelId(1);
        outputLabelRecommendation.setUserId(1);
        outputLabelRecommendation.setLiked(true);


        outputLabelRecommendation2 = new LabelRecommendation();
        outputLabelRecommendation2.setId(2);
        outputLabelRecommendation.setLabelId(1);
        outputLabelRecommendation.setUserId(1);
        outputLabelRecommendation.setLiked(false);

        allLabelRecommendations = new ArrayList<>(Arrays.asList(
                outputLabelRecommendation,
                outputLabelRecommendation2
        ));

        doReturn(outputLabelRecommendation).when(labelRecommendationRepository).save(inputLabelRecommendation);
        doReturn(Optional.of(outputLabelRecommendation)).when(labelRecommendationRepository).findById(1);
        doReturn(allLabelRecommendations).when(labelRecommendationRepository).findAll();
    }

    @Test
    public void shouldAddLabelRecommendationOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputLabelRecommendation);
        String outputJson = mapper.writeValueAsString(outputLabelRecommendation);

        mockMvc.perform(post("/labelrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetLabelRecommendationById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputLabelRecommendation);

        mockMvc.perform(get("/labelrecommendation/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllLabelRecommendations() throws Exception {
        String outputJson = mapper.writeValueAsString(allLabelRecommendations);

        mockMvc.perform(get("/labelrecommendation"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingLabelRecommendation() throws Exception {
        inputLabelRecommendation.setId(1);
        inputLabelRecommendation.setLiked(false);

        String inputJson = mapper.writeValueAsString(inputLabelRecommendation);

        mockMvc.perform(put("/labelrecommendation")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingLabelRecommendation() throws Exception {
        mockMvc.perform(delete("/labelrecommendation/1"))
                .andExpect(status().isNoContent());
    }
}