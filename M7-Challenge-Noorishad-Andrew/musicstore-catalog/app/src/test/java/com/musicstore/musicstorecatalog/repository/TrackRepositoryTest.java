package com.musicstore.musicstorecatalog.repository;

import com.musicstore.musicstorecatalog.model.Album;
import com.musicstore.musicstorecatalog.model.Artist;
import com.musicstore.musicstorecatalog.model.Label;
import com.musicstore.musicstorecatalog.model.Track;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository repo;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();

    }

    @Test
    public void shouldCreateGetAndDeleteTrack() {
        Artist outputArtist = new Artist();
        outputArtist.setName("My Name");
        outputArtist.setInstagram("Insta");
        outputArtist.setTwitter("Tweet");
        artistRepository.save(outputArtist);


        Label outputLabel = new Label();
        outputLabel.setName("My Name");
        outputLabel.setWebsite("www.Insta.com");
        labelRepository.save(outputLabel);

        Album inputAlbum = new Album();
        inputAlbum.setTitle("My Title");
        inputAlbum.setArtistId(outputArtist.getId());
        inputAlbum.setReleaseDate(LocalDate.parse("1111-12-31"));
        inputAlbum.setLabelId(outputLabel.getId());
        inputAlbum.setListPrice(BigDecimal.valueOf(19.99));
        albumRepository.save(inputAlbum);

        Track inputTrack = new Track();
        inputTrack.setAlbumId(inputAlbum.getId());
        inputTrack.setTitle("My Title");
        inputTrack.setRunTime(200);

        inputTrack = repo.save(inputTrack);

        Track whatIGot = repo.findById(inputTrack.getId()).get();

        assertEquals(inputTrack, whatIGot);

        repo.deleteById(inputTrack.getId());

        Optional<Track> shouldBeEmptyOptional = repo.findById(inputTrack.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }
}