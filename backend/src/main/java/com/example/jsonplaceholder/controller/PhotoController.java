package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.dto.CreatePhotoDto;
import com.example.jsonplaceholder.dto.UpdatePhotoDto;
import com.example.jsonplaceholder.dto.PhotoResponseDto;
import com.example.jsonplaceholder.mapper.DtoMapper;
import com.example.jsonplaceholder.model.Album;
import com.example.jsonplaceholder.model.Photo;
import com.example.jsonplaceholder.service.AlbumService;
import com.example.jsonplaceholder.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "*")
public class PhotoController {
    
    @Autowired
    private PhotoService photoService;
    
    @Autowired
    private AlbumService albumService;
    
    @Autowired
    private DtoMapper dtoMapper;
    
    // GET all photos
    @GetMapping
    public ResponseEntity<List<PhotoResponseDto>> getAllPhotos(@RequestParam(required = false) Integer albumId) {
        List<Photo> photos;
        if (albumId != null) {
            photos = photoService.getPhotosByAlbumId(albumId);
        } else {
            photos = photoService.getAllPhotos();
        }
        List<PhotoResponseDto> photoDtos = photos.stream()
            .map(dtoMapper::toPhotoResponseDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(photoDtos, HttpStatus.OK);
    }
    
    // GET photo by ID
    @GetMapping("/{id}")
    public ResponseEntity<PhotoResponseDto> getPhotoById(@PathVariable Integer id) {
        return photoService.getPhotoById(id)
            .map(photo -> new ResponseEntity<>(dtoMapper.toPhotoResponseDto(photo), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new photo
    @PostMapping
    public ResponseEntity<PhotoResponseDto> createPhoto(@Valid @RequestBody CreatePhotoDto createPhotoDto) {
        try {
            Album album = albumService.getAlbumById(createPhotoDto.getAlbumId())
                .orElseThrow(() -> new RuntimeException("Album not found"));
            
            Photo photo = new Photo();
            photo.setAlbum(album);
            photo.setTitle(createPhotoDto.getTitle());
            photo.setUrl(createPhotoDto.getUrl());
            photo.setThumbnailUrl(createPhotoDto.getThumbnailUrl());
            
            Photo createdPhoto = photoService.createPhoto(photo);
            return new ResponseEntity<>(dtoMapper.toPhotoResponseDto(createdPhoto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire photo
    @PutMapping("/{id}")
    public ResponseEntity<PhotoResponseDto> updatePhoto(@PathVariable Integer id, @Valid @RequestBody UpdatePhotoDto updatePhotoDto) {
        try {
            Photo existingPhoto = photoService.getPhotoById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
            
            existingPhoto.setTitle(updatePhotoDto.getTitle());
            existingPhoto.setUrl(updatePhotoDto.getUrl());
            existingPhoto.setThumbnailUrl(updatePhotoDto.getThumbnailUrl());
            
            Photo updatedPhoto = photoService.updatePhoto(id, existingPhoto);
            return new ResponseEntity<>(dtoMapper.toPhotoResponseDto(updatedPhoto), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update photo
    @PatchMapping("/{id}")
    public ResponseEntity<PhotoResponseDto> patchPhoto(@PathVariable Integer id, @RequestBody UpdatePhotoDto updatePhotoDto) {
        try {
            Photo existingPhoto = photoService.getPhotoById(id)
                .orElseThrow(() -> new RuntimeException("Photo not found"));
            
            dtoMapper.updatePhotoFromDto(existingPhoto, updatePhotoDto);
            Photo patchedPhoto = photoService.patchPhoto(id, existingPhoto);
            return new ResponseEntity<>(dtoMapper.toPhotoResponseDto(patchedPhoto), HttpStatus.OK);
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
