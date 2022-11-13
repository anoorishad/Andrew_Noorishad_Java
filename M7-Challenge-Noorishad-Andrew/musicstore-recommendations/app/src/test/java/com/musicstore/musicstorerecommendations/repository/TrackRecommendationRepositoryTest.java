package com.musicstore.musicstorerecommendations.repository;


import com.musicstore.musicstorerecommendations.model.TrackRecommendation;
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
public class TrackRecommendationRepositoryTest {
    @Autowired
    private TrackRecommendationRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteTrackRecommendation() {
        TrackRecommendation inputTrackRecommendation = new TrackRecommendation();
        inputTrackRecommendation.setTrackId(1);
        inputTrackRecommendation.setUserId(1);
        inputTrackRecommendation.setLiked(true);

        inputTrackRecommendation = repo.save(inputTrackRecommendation);

        TrackRecommendation whatIGot = repo.findById(inputTrackRecommendation.getId()).get();

        assertEquals(inputTrackRecommendation, whatIGot);

        repo.deleteById(inputTrackRecommendation.getId());

        Optional<TrackRecommendation> shouldBeEmptyOptional = repo.findById(inputTrackRecommendation.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}