package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/location")
public class LocationController {

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

    @GetMapping(path = "/municipality/{stateCode}")
    public List<Location> getLocationByStateCode(@PathVariable String stateCode) {
        return locationService.getLocationByStateCode(stateCode);
    }

    @GetMapping(path = "/state/{code}")
    public List<Location> getLocationStateByCode(@PathVariable String code) {
        return locationService.getLocationStateByCode(code);
    }

    @GetMapping(path="/capitals")
    public List<Location> getLocationCapitals() {
        return locationService.getLocationCapitals();
    }

    @GetMapping(path="/states")
    public List<Location> getStates() {
        return locationService.getStates();
    }

    @GetMapping(path = "/letters/{initial}/{end}")
    public List<Location> getLocationByLetters(@PathVariable String initial, @PathVariable String end) {
        return locationService.getLocationByLetters(initial,end);
    }
}


