package com.musicstore.musicstorecatalog.controller;

import com.musicstore.musicstorecatalog.model.Album;
import com.musicstore.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumRepository repo;

    @GetMapping()
    public List<Album> getAlbums() {return repo.findAll();}

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable int id) {
        Optional<Album> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Album addAlbum(@RequestBody Album album) {
        return repo.save(album);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody Album album) {
        repo.save(album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable int id) {
        repo.deleteById(id);
    }

}
