package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.model.Photo;
import com.example.jsonplaceholder.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
public class PhotoController {
    
    @Autowired
    private PhotoService photoService;
    
    // GET all photos
    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos(@RequestParam(required = false) Integer albumId) {
        List<Photo> photos;
        if (albumId != null) {
            photos = photoService.getPhotosByAlbumId(albumId);
        } else {
            photos = photoService.getAllPhotos();
        }
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
    
    // GET photo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Integer id) {
        return photoService.getPhotoById(id)
            .map(photo -> new ResponseEntity<>(photo, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new photo
    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
        try {
            Photo createdPhoto = photoService.createPhoto(photo);
            return new ResponseEntity<>(createdPhoto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire photo
    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable Integer id, @RequestBody Photo photo) {
        try {
            Photo updatedPhoto = photoService.updatePhoto(id, photo);
            return new ResponseEntity<>(updatedPhoto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update photo
    @PatchMapping("/{id}")
    public ResponseEntity<Photo> patchPhoto(@PathVariable Integer id, @RequestBody Photo photo) {
        try {
            Photo patchedPhoto = photoService.patchPhoto(id, photo);
            return new ResponseEntity<>(patchedPhoto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE photo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Integer id) {
        try {
            photoService.deletePhoto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
