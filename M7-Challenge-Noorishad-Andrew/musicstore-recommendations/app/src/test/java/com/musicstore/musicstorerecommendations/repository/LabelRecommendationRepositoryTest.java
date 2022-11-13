package com.musicstore.musicstorerecommendations.repository;

import com.musicstore.musicstorerecommendations.model.LabelRecommendation;
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
public class LabelRecommendationRepositoryTest {

    @Autowired
    private LabelRecommendationRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteLabelRecommendation() {
        LabelRecommendation inputLabelRecommendation = new LabelRecommendation();
        inputLabelRecommendation.setLabelId(1);
        inputLabelRecommendation.setUserId(1);
        inputLabelRecommendation.setLiked(true);

        inputLabelRecommendation = repo.save(inputLabelRecommendation);

        LabelRecommendation whatIGot = repo.findById(inputLabelRecommendation.getId()).get();

        assertEquals(inputLabelRecommendation, whatIGot);

        repo.deleteById(inputLabelRecommendation.getId());

        Optional<LabelRecommendation> shouldBeEmptyOptional = repo.findById(inputLabelRecommendation.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}