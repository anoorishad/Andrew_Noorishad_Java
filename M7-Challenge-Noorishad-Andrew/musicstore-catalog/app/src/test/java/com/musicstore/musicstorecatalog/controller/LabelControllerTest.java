package com.musicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorecatalog.model.Artist;
import com.musicstore.musicstorecatalog.model.Label;
import com.musicstore.musicstorecatalog.repository.ArtistRepository;
import com.musicstore.musicstorecatalog.repository.LabelRepository;
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
@WebMvcTest(LabelController.class)
public class LabelControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LabelRepository labelRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Label inputLabel;
    Label outputLabel;
    Label outputLabel2;

    List<Label> allLabels;

    @Before
    public void setUp() throws Exception {
        inputLabel = new Label();
        inputLabel.setName("My Name");
        inputLabel.setWebsite("www.Insta.com");

        outputLabel = new Label();
        outputLabel.setId(1);
        outputLabel.setName("My Name");
        outputLabel.setWebsite("www.Insta.com");

        outputLabel2 = new Label();
        outputLabel2.setId(1);
        outputLabel2.setName("My Other Name");
        outputLabel2.setWebsite("www.Insta2.com");

        allLabels = new ArrayList<>(Arrays.asList(
                outputLabel,
                outputLabel2
        ));

        doReturn(outputLabel).when(labelRepository).save(inputLabel);
        doReturn(Optional.of(outputLabel)).when(labelRepository).findById(1);
        doReturn(allLabels).when(labelRepository).findAll();
    }

    @Test
    public void shouldAddLabelOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputLabel);
        String outputJson = mapper.writeValueAsString(outputLabel);

        mockMvc.perform(post("/label")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetLabelById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputLabel);

        mockMvc.perform(get("/label/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllLabels() throws Exception {
        String outputJson = mapper.writeValueAsString(allLabels);

        mockMvc.perform(get("/label"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingLabel() throws Exception {
        inputLabel.setId(1);
        inputLabel.setName("New Name");

        String inputJson = mapper.writeValueAsString(inputLabel);

        mockMvc.perform(put("/label")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingLabel() throws Exception {
        mockMvc.perform(delete("/label/1"))
                .andExpect(status().isNoContent());
    }
}