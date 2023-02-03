package com.maybenot.oeh.controllers;

import com.maybenot.oeh.entities.Place;
import com.maybenot.oeh.entities.User;
import com.maybenot.oeh.services.PlaceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @ApiOperation(value = "Get place by ID")
    @GetMapping(path = "place/{placeId}")
    public Place getPlace(@PathVariable("placeId") String placeId){
        return placeService.getPlace(placeId);
    }

    @ApiOperation(value = "Get all places")
    @GetMapping(path = "places")
    public List<Place> getPlaces(){
        return placeService.getPlaces();
    }

    @ApiOperation(value = "Register new place")
    @PostMapping(path = "registerPlace")
    public void registerPlace(@RequestBody Place place){
        placeService.registerPlace(place);
    }

    @ApiOperation(value = "Update place")
    @PutMapping(path = "updatePlace/{placeId}")
    public void updatePlace(@PathVariable("placeId") String placeId, @RequestParam String name, @RequestParam Place.Coordinate coordinate){
        placeService.updatePlace(placeId, name, coordinate);
    }

    @ApiOperation(value = "Delete place by ID")
    @DeleteMapping(path = "deletePlace/{placeId}")
    public void deletePlace(@PathVariable("placeId") String placeId){
        placeService.deletePlace(placeId);
    }
}
