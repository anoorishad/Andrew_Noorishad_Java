package com.musicstore.musicstorecatalog.repository;

import com.musicstore.musicstorecatalog.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteArtist() {
        Artist inputArtist = new Artist();
        inputArtist.setName("My Name");
        inputArtist.setInstagram("Insta");
        inputArtist.setTwitter("Tweet");

        inputArtist = repo.save(inputArtist);

        Artist whatIGot = repo.findById(inputArtist.getId()).get();

        assertEquals(inputArtist, whatIGot);

        repo.deleteById(inputArtist.getId());

        Optional<Artist> shouldBeEmptyOptional = repo.findById(inputArtist.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}