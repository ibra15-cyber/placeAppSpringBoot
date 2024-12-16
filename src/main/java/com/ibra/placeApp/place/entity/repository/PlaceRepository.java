package com.ibra.placeApp.place.entity.repository;

import com.ibra.placeApp.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}

//public interface PlaceRepository extends MongoRepository<Place, Long> {
//}
