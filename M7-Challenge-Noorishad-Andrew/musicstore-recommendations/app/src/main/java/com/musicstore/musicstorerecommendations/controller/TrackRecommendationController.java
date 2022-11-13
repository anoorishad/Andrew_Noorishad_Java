package com.musicstore.musicstorerecommendations.controller;


import com.musicstore.musicstorerecommendations.model.TrackRecommendation;
import com.musicstore.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trackrecommendation")
public class TrackRecommendationController {
    @Autowired
    TrackRecommendationRepository repo;

    @GetMapping()
    public List<TrackRecommendation> getTrackRecommendations() {return repo.findAll();}

    @GetMapping("/{id}")
    public TrackRecommendation getTrackRecommendationById(@PathVariable int id) {
        Optional<TrackRecommendation> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation addTrackRecommendation(@RequestBody TrackRecommendation trackRecommendation) {
        return repo.save(trackRecommendation);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@RequestBody TrackRecommendation trackRecommendation) {
        repo.save(trackRecommendation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable int id) {
        repo.deleteById(id);
    }
}
