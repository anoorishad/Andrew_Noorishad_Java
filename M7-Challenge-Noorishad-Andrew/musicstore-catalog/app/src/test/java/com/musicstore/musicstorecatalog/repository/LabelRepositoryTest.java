package com.musicstore.musicstorecatalog.repository;

import com.musicstore.musicstorecatalog.model.Label;
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
public class LabelRepositoryTest {
    @Autowired
    private LabelRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteLabel() {
        Label inputLabel = new Label();
        inputLabel.setName("My Name");
        inputLabel.setWebsite("www.Insta.com");

        inputLabel = repo.save(inputLabel);

        Label whatIGot = repo.findById(inputLabel.getId()).get();

        assertEquals(inputLabel, whatIGot);

        repo.deleteById(inputLabel.getId());

        Optional<Label> shouldBeEmptyOptional = repo.findById(inputLabel.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}