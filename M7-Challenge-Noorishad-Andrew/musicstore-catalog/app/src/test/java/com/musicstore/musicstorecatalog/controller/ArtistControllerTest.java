package com.musicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorecatalog.model.Artist;
import com.musicstore.musicstorecatalog.repository.ArtistRepository;
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
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArtistRepository artistRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Artist inputArtist;
    Artist outputArtist;
    Artist outputArtist2;

    List<Artist> allArtists;

    @Before
    public void setUp() throws Exception {
        inputArtist = new Artist();
        inputArtist.setName("My Name");
        inputArtist.setInstagram("Insta");
        inputArtist.setTwitter("Tweet");

        outputArtist = new Artist();
        outputArtist.setId(1);
        outputArtist.setName("My Name");
        outputArtist.setInstagram("Insta");
        outputArtist.setTwitter("Tweet");

        outputArtist2 = new Artist();
        outputArtist2.setId(1);
        outputArtist2.setName("My Other Name");
        outputArtist2.setInstagram("Insta 2");
        outputArtist2.setTwitter("Tweet 2");

        allArtists = new ArrayList<>(Arrays.asList(
                outputArtist,
                outputArtist2
        ));

        doReturn(outputArtist).when(artistRepository).save(inputArtist);
        doReturn(Optional.of(outputArtist)).when(artistRepository).findById(1);
        doReturn(allArtists).when(artistRepository).findAll();
    }

    @Test
    public void shouldAddArtistOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputArtist);
        String outputJson = mapper.writeValueAsString(outputArtist);

        mockMvc.perform(post("/artist")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetArtistById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputArtist);

        mockMvc.perform(get("/artist/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllArtists() throws Exception {
        String outputJson = mapper.writeValueAsString(allArtists);

        mockMvc.perform(get("/artist"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingArtist() throws Exception {
        inputArtist.setId(1);
        inputArtist.setName("New Name");

        String inputJson = mapper.writeValueAsString(inputArtist);

        mockMvc.perform(put("/artist")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingArtist() throws Exception {
        mockMvc.perform(delete("/artist/1"))
                .andExpect(status().isNoContent());
    }
}