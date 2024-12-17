package com.br.musicasbackend.repository;

import com.br.musicasbackend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<User> findByName(String name);

    @Query("{ 'cargo': { $regex: ?0, $options: 'i' } }")
    List<User>findByCargo(String cargo);
}
