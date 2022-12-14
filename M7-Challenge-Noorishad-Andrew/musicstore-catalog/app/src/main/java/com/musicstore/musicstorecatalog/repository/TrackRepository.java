package com.musicstore.musicstorecatalog.repository;


import com.musicstore.musicstorecatalog.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
}
