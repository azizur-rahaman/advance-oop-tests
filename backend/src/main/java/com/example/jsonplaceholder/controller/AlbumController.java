package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.model.Album;
import com.example.jsonplaceholder.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "*")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    // GET all albums
    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(@RequestParam(required = false) Integer userId) {
        List<Album> albums;
        if (userId != null) {
            albums = albumService.getAlbumsByUserId(userId);
        } else {
            albums = albumService.getAllAlbums();
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
    
    // GET album by ID
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Integer id) {
        return albumService.getAlbumById(id)
            .map(album -> new ResponseEntity<>(album, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new album
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        try {
            Album createdAlbum = albumService.createAlbum(album);
            return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire album
    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Integer id, @RequestBody Album album) {
        try {
            Album updatedAlbum = albumService.updateAlbum(id, album);
            return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update album
    @PatchMapping("/{id}")
    public ResponseEntity<Album> patchAlbum(@PathVariable Integer id, @RequestBody Album album) {
        try {
            Album patchedAlbum = albumService.patchAlbum(id, album);
            return new ResponseEntity<>(patchedAlbum, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE album
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        try {
            albumService.deleteAlbum(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
