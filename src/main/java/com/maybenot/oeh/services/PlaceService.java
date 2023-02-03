package com.maybenot.oeh.services;

import com.maybenot.oeh.entities.Place;
import com.maybenot.oeh.entities.User;
import com.maybenot.oeh.exception.ResourceNotFoundException;
import com.maybenot.oeh.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlaceService {

    private static final String PLACE = "Place";

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Place getPlace(String id){
        return placeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(PLACE, id));
    }

    public List<Place> getPlaces(){
        List<Place> places = new ArrayList<>();
        placeRepository.findAll().forEach(places::add);
        return places;
    }

    public void registerPlace(Place place) {
        placeRepository.save(place);
    }

    public void updatePlace(String id, String name, Place.Coordinate coordinate) {
        Place place = placeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(PLACE, id));
        boolean isChanged = false;

        if(name != null && name.length()>0 && !Objects.equals(place.getName(), name)){
            place.setName(name);
            isChanged = true;
        }

        if(coordinate != null && !Objects.equals(place.getCoordinate(), coordinate)){
            place.setCoordinate(coordinate);
            isChanged = true;
        }

        if (isChanged) {
            placeRepository.save(place);
        }
    }

    public void deletePlace(String id){
        Place placeById = placeRepository.findById(id).orElseThrow(
                () ->new ResourceNotFoundException(PLACE, id));
        placeRepository.delete(placeById);
    }
}
