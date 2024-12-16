package com.ibra.placeApp.place.entity.mapper;

import com.ibra.placeApp.place.entity.Place;
import com.ibra.placeApp.place.entity.dto.PlaceDto;

public class PlaceMapper {

    public  static PlaceDto mapToPlaceDto(Place place) {
        PlaceDto placeDto = new PlaceDto();

        placeDto.setDescription(place.getDescription());
        placeDto.setTitle(place.getTitle());
        placeDto.setImage(place.getImage());
        placeDto.setCategory(place.getCategory());
        placeDto.setAddress(place.getAddress());
        placeDto.setLocation(place.getLocation());
        return placeDto;
    }


    public static Place mapToPlace(PlaceDto placeDto) {
        Place place = new Place();

        place.setTitle(placeDto.getTitle());
        place.setDescription(placeDto.getDescription());
        place.setCategory(placeDto.getCategory());
        place.setImage(placeDto.getImage());
        place.setAddress(placeDto.getAddress());
        place.setLocation(placeDto.getLocation());
        return place;
    }
}
