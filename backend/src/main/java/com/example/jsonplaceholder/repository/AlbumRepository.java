package com.example.jsonplaceholder.repository;

import com.example.jsonplaceholder.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findByUserId(Integer userId);
}
