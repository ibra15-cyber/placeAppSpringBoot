package com.ibra.placeApp.place.entity.controller;

import com.ibra.placeApp.place.entity.Place;
import com.ibra.placeApp.place.entity.dto.PlaceDto;
import com.ibra.placeApp.place.entity.repository.PlaceRepository;
import com.ibra.placeApp.place.entity.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlaceController {


    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }


    @GetMapping("/api/public/place")
    public ResponseEntity<List<Place>> getAllPlaces(){
        List<Place> places =  placeService.getAllPlaces();
        System.out.println(places);
        return ResponseEntity.status(HttpStatus.OK).body(places);
    }

    @GetMapping("/api/public/place/{id}")
    public ResponseEntity<?> getPlaceById(@PathVariable("id") Long id){
        try {
            Place foundPlace = placeService.getPlaceById(id);
            return ResponseEntity.status(HttpStatus.OK).body(foundPlace);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("item does not exist " + e.getMessage());
        }

    }

    @PostMapping("/api/public/place")
    public ResponseEntity<Place> createPlace(@RequestBody PlaceDto placeDto){
        Place cratePlace =  placeService.createPlace(placeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cratePlace);
    }

    @PutMapping("/api/public/place/{id}")
    public ResponseEntity<?> updatePlace(@PathVariable("id") Long id, @RequestBody Place place){
        try {
            Place updatedPlace = placeService.updatePlace(id, place);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPlace);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found " + e.getMessage());
        }

    }

    @DeleteMapping("/api/public/place/{id}")
    public ResponseEntity<String> deletePlace(@PathVariable("id") Long id){
         Place deletedItem = placeService.deletePlace(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedItem.getTitle() + " deleted successfully!");
    }


    @GetMapping("/api/hello")
    public String Hello() {
        return "Hello world";
    }




}
