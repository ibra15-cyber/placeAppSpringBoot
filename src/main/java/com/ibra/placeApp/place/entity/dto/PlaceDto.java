package com.ibra.placeApp.place.entity.dto;

import com.ibra.placeApp.place.entity.Location;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlaceDto {
    private String title;
    private String description;
    private String image;
    private String address;
    private String category;
    private Location location;
    private Long creatorId; // Only the ID of the creator

}
