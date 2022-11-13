package com.musicstore.musicstorecatalog.repository;

import com.musicstore.musicstorecatalog.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}
