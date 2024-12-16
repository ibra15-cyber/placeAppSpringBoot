package com.ibra.placeApp.user.repository;

import com.ibra.placeApp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
//
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

//public interface UserRepository extends MongoRepository<User, Long> {
//    Optional<User> findByUsername(String username);
//}