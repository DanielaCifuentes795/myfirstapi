package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.State;
import co.edu.umanizales.myfirstapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/location")
public class LocationController {
/*
    @GetMapping
    public Location getLocation() {
        Location location = new Location("1","Manizales");
            return location;
    }

    //Método GET para devolver una lista de Location
    @GetMapping
    public List<Location> getLocations() {
        return Arrays.asList(
                new Location("001","Location 1"),
                new Location("002","Location 2"),
                new Location("003","Location 3")
        );
    }

 */

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getLocations() {
        return locationService.getLocations();
    }

    @GetMapping(path = "/{code}")
    public Location getLocationByCode(@PathVariable String code) {
        return locationService.getLocationByCode(code);
    }

    @GetMapping(path = "/name/{name}")
    public Location getLocationByName(@PathVariable String name) {
        return locationService.getLocationByName(name);
    }

    @GetMapping(path = "/initial-letter/{letter}")
    public List<Location> getLocationByInitialLetter(@PathVariable String letter) {
        return locationService.getLocationsByInitialLetter(letter);
    }

    @GetMapping(path = "/municipality/{statecode}")
    public List<Location> getLocationByStateCode(@PathVariable String statecode) {
        return locationService.getLocationByStateCode(statecode);
    }

    @GetMapping(path = "/state/{code}")
    public State getLocationStateByCode(@PathVariable String code) {
        return locationService.getLocationStateByCode(code);
    }

    @GetMapping(path="/capitals")
    public List<Location> getLocationCapitals() {
        return locationService.getLocationCapitals();
    }

    @GetMapping(path="/states")
    public List<State> getLocationStates() {
        return locationService.getLocationStates();
    }

    @GetMapping(path = "/letters/{initial}/{end}")
    public List<Location> getLocationByLetters(@PathVariable String initial, @PathVariable String end) {
        return locationService.getLocationByLetters(initial,end);
    }
}


