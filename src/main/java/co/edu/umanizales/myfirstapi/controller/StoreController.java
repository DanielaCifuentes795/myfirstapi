package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Store;
import co.edu.umanizales.myfirstapi.service.LocationService;
import co.edu.umanizales.myfirstapi.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Store> getStore() {
        return storeService.getStore();
    }

    @GetMapping(path = "/name/{name}")
    public Location getLocationByName(@PathVariable String name) {
        return locationService.getLocationByName(name);
    }
}