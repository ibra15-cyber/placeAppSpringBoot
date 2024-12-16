package com.ibra.placeApp.place.entity.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ibra.placeApp.place.entity.Location;
import com.ibra.placeApp.place.entity.Place;
import com.ibra.placeApp.place.entity.dto.PlaceDto;
import com.ibra.placeApp.place.entity.mapper.PlaceMapper;
import com.ibra.placeApp.place.entity.repository.PlaceRepository;
import com.ibra.placeApp.place.entity.util.GoogleGeolocation;
import com.ibra.placeApp.user.entity.User;
import com.ibra.placeApp.user.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
//    private final GoogleGeolocation googleGeolocation;

    public PlaceService(PlaceRepository placeRepository, UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
//        this.googleGeolocation = googleGeolocation;
    }


    //GET ALL
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }


    //Create place
    public Place createPlace(PlaceDto placeDto) {
        Long userId = placeDto.getCreatorId();  // placeDto has a field creatorId
        System.out.print("user id " + userId.toString());

        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User creator = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Map the PlaceDto to Place entity
        Place place = PlaceMapper.mapToPlace(placeDto);
        place.setCreator(creator);

        // Get coordinates asynchronously
//        Mono<JSONObject> json = googleGeolocation.getCoordinatesFromAddress(placeDto.getAddress());
//
//        // Block to get the result synchronously
//        JSONObject coordinates = json.block();
//
//        if (coordinates != null) {
//            // Assuming that your location is in the "geometry" -> "location" part\
//            System.out.print(coordinates);
//            double latitude = coordinates.getDouble("lat");
//            double longitude = coordinates.getDouble("lng");

            // Create Location object and set it
//            Location location = new Location(latitude, longitude);
//            place.setLocation(location);
//        }


        return placeRepository.save(place);
    }



    public Place getPlaceById(Long id) {
        Place findplace = placeRepository.findById(id).orElseThrow();
        return findplace;
    }

    public Place updatePlace(Long id, Place place) {
        Place foundPlace = placeRepository.findById(id).orElseThrow();
        foundPlace.setDescription(place.getDescription());
        foundPlace.setTitle(place.getTitle());
        return placeRepository.save(foundPlace);

    }

    public Place deletePlace(Long id) {
        Place findPlace = placeRepository.findById(id).orElseThrow();
        placeRepository.delete(findPlace);
        return findPlace;
    }
}

