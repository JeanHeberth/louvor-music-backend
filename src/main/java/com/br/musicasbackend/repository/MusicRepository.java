package com.br.musicasbackend.repository;

import com.br.musicasbackend.entity.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MusicRepository extends MongoRepository<Music, String> {
    @Query("{ 'music': { $regex: ?0, $options: 'i' } }")
    List<Music> findByMusic(String music);
}
