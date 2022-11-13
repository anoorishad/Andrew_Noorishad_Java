package com.musicstore.musicstorerecommendations.repository;

import com.musicstore.musicstorerecommendations.model.ArtistRecommendation;
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
public class ArtistRecommendationRepositoryTest {

    @Autowired
    private ArtistRecommendationRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteArtistRecommendation() {
        ArtistRecommendation inputArtistRecommendation = new ArtistRecommendation();
        inputArtistRecommendation.setArtistId(1);
        inputArtistRecommendation.setUserId(1);
        inputArtistRecommendation.setLiked(true);

        inputArtistRecommendation = repo.save(inputArtistRecommendation);

        ArtistRecommendation whatIGot = repo.findById(inputArtistRecommendation.getId()).get();

        assertEquals(inputArtistRecommendation, whatIGot);

        repo.deleteById(inputArtistRecommendation.getId());

        Optional<ArtistRecommendation> shouldBeEmptyOptional = repo.findById(inputArtistRecommendation.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}