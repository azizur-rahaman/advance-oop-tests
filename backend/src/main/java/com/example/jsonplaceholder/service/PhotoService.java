package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.Photo;
import com.example.jsonplaceholder.model.Album;
import com.example.jsonplaceholder.repository.PhotoRepository;
import com.example.jsonplaceholder.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    
    @Autowired
    private PhotoRepository photoRepository;
    
    @Autowired
    private AlbumRepository albumRepository;
    
    // GET all photos
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
    
    // GET photo by ID
    public Optional<Photo> getPhotoById(Integer id) {
        return photoRepository.findById(id);
    }
    
    // GET photos by album ID
    public List<Photo> getPhotosByAlbumId(Integer albumId) {
        return photoRepository.findByAlbumId(albumId);
    }
    
    // POST - Create new photo
    public Photo createPhoto(Photo photo) {
        if (photo.getAlbum() != null && photo.getAlbum().getId() != null) {
            Album album = albumRepository.findById(photo.getAlbum().getId())
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + photo.getAlbum().getId()));
            photo.setAlbum(album);
        }
        return photoRepository.save(photo);
    }
    
    // PUT - Update entire photo
    public Photo updatePhoto(Integer id, Photo photoDetails) {
        Photo photo = photoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Photo not found with id: " + id));
        
        if (photoDetails.getAlbum() != null && photoDetails.getAlbum().getId() != null) {
            Album album = albumRepository.findById(photoDetails.getAlbum().getId())
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + photoDetails.getAlbum().getId()));
            photo.setAlbum(album);
        }
        photo.setTitle(photoDetails.getTitle());
        photo.setUrl(photoDetails.getUrl());
        photo.setThumbnailUrl(photoDetails.getThumbnailUrl());
        
        return photoRepository.save(photo);
    }
    
    // PATCH - Partially update photo
    public Photo patchPhoto(Integer id, Photo photoDetails) {
        Photo photo = photoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Photo not found with id: " + id));
        
        if (photoDetails.getAlbum() != null && photoDetails.getAlbum().getId() != null) {
            Album album = albumRepository.findById(photoDetails.getAlbum().getId())
                .orElseThrow(() -> new RuntimeException("Album not found with id: " + photoDetails.getAlbum().getId()));
            photo.setAlbum(album);
        }
        if (photoDetails.getTitle() != null) {
            photo.setTitle(photoDetails.getTitle());
        }
        if (photoDetails.getUrl() != null) {
            photo.setUrl(photoDetails.getUrl());
        }
        if (photoDetails.getThumbnailUrl() != null) {
            photo.setThumbnailUrl(photoDetails.getThumbnailUrl());
        }
        
        return photoRepository.save(photo);
    }
    
    // DELETE photo
    public void deletePhoto(Integer id) {
        Photo photo = photoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Photo not found with id: " + id));
        photoRepository.delete(photo);
    }
}
