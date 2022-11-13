package com.musicstore.musicstorerecommendations.repository;

import com.musicstore.musicstorerecommendations.model.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {
    @Autowired
    private AlbumRecommendationRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteAlbumRecommendation() {
        AlbumRecommendation inputAlbumRecommendation = new AlbumRecommendation();
        inputAlbumRecommendation.setAlbumId(1);
        inputAlbumRecommendation.setUserId(1);
        inputAlbumRecommendation.setLiked(true);

        inputAlbumRecommendation = repo.save(inputAlbumRecommendation);

        AlbumRecommendation whatIGot = repo.findById(inputAlbumRecommendation.getId()).get();

        assertEquals(inputAlbumRecommendation, whatIGot);

        repo.deleteById(inputAlbumRecommendation.getId());

        Optional<AlbumRecommendation> shouldBeEmptyOptional = repo.findById(inputAlbumRecommendation.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}