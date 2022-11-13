package com.musicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorecatalog.model.Album;
import com.musicstore.musicstorecatalog.model.Track;
import com.musicstore.musicstorecatalog.repository.AlbumRepository;
import com.musicstore.musicstorecatalog.repository.TrackRepository;
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
@WebMvcTest(TrackController.class)
public class TrackControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrackRepository trackRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Track inputTrack;
    Track outputTrack;
    Track outputTrack2;

    List<Track> allTracks;

    @Before
    public void setUp() throws Exception {
        inputTrack = new Track();
        inputTrack.setAlbumId(1);
        inputTrack.setTitle("My Title");
        inputTrack.setRunTime(200);

        outputTrack = new Track();
        outputTrack.setId(1);
        outputTrack.setAlbumId(1);
        outputTrack.setTitle("My Title");
        outputTrack.setRunTime(200);

        outputTrack2 = new Track();
        outputTrack2.setId(2);
        outputTrack2.setAlbumId(2);
        outputTrack2.setTitle("My Title 2");
        outputTrack2.setRunTime(250);

        allTracks = new ArrayList<>(Arrays.asList(
                outputTrack,
                outputTrack2
        ));

        doReturn(outputTrack).when(trackRepository).save(inputTrack);
        doReturn(Optional.of(outputTrack)).when(trackRepository).findById(1);
        doReturn(allTracks).when(trackRepository).findAll();
    }

    @Test
    public void shouldAddTrackOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputTrack);
        String outputJson = mapper.writeValueAsString(outputTrack);

        mockMvc.perform(post("/track")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTrackById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTrack);

        mockMvc.perform(get("/track/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllTracks() throws Exception {
        String outputJson = mapper.writeValueAsString(allTracks);

        mockMvc.perform(get("/track"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingTrack() throws Exception {
        inputTrack.setId(1);
        inputTrack.setAlbumId(5);

        String inputJson = mapper.writeValueAsString(inputTrack);

        mockMvc.perform(put("/track")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingTrack() throws Exception {
        mockMvc.perform(delete("/track/1"))
                .andExpect(status().isNoContent());
    }
}