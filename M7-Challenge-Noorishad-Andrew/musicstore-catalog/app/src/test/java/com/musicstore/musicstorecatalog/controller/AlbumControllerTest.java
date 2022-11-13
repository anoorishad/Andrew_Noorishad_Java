package com.musicstore.musicstorecatalog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicstore.musicstorecatalog.model.Album;
import com.musicstore.musicstorecatalog.repository.AlbumRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlbumRepository albumRepository;

    private ObjectMapper mapper = new ObjectMapper();

    Album inputAlbum;
    Album outputAlbum;
    Album outputAlbum2;

    List<Album> allAlbums;

    @Before
    public void setUp() throws Exception {
        inputAlbum = new Album();
        inputAlbum.setTitle("My Title");
        inputAlbum.setArtistId(1);
        inputAlbum.setReleaseDate(LocalDate.parse("1111-12-31"));
        inputAlbum.setLabelId(1);
        inputAlbum.setListPrice(BigDecimal.valueOf(19.99));

        outputAlbum = new Album();
        outputAlbum.setId(1);
        outputAlbum.setTitle("My Title");
        outputAlbum.setArtistId(1);
        outputAlbum.setReleaseDate(LocalDate.parse("1111-12-31"));
        outputAlbum.setLabelId(1);
        outputAlbum.setListPrice(BigDecimal.valueOf(19.99));

        outputAlbum2 = new Album();
        outputAlbum2.setId(2);
        outputAlbum2.setTitle("My Other Title");
        outputAlbum2.setArtistId(1);
        outputAlbum2.setReleaseDate(LocalDate.parse("2222-12-31"));
        outputAlbum2.setLabelId(1);
        outputAlbum2.setListPrice(BigDecimal.valueOf(29.99));

        allAlbums = new ArrayList<>(Arrays.asList(
                outputAlbum,
                outputAlbum2
        ));

        doReturn(outputAlbum).when(albumRepository).save(inputAlbum);
        doReturn(Optional.of(outputAlbum)).when(albumRepository).findById(1);
        doReturn(allAlbums).when(albumRepository).findAll();
    }

    @Test
    public void shouldAddAlbumOnPostRequest() throws Exception {
        String inputJson = mapper.writeValueAsString(inputAlbum);
        String outputJson = mapper.writeValueAsString(outputAlbum);

        mockMvc.perform(post("/album")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andDo(print())
                        .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAlbumById() throws Exception {
        String outputJson = mapper.writeValueAsString(outputAlbum);

        mockMvc.perform(get("/album/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldGetAllAlbums() throws Exception {
        String outputJson = mapper.writeValueAsString(allAlbums);

        mockMvc.perform(get("/album"))
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldRespondWith204WhenUpdatingAlbum() throws Exception {
        inputAlbum.setId(1);
        inputAlbum.setLabelId(3);

        String inputJson = mapper.writeValueAsString(inputAlbum);

        mockMvc.perform(put("/album")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldRespondWith204WhenDeletingAlbum() throws Exception {
        mockMvc.perform(delete("/album/1"))
                .andExpect(status().isNoContent());
    }

}