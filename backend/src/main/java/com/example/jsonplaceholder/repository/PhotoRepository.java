package com.example.jsonplaceholder.repository;

import com.example.jsonplaceholder.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByAlbumId(Integer albumId);
}
