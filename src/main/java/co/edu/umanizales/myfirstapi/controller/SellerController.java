package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Seller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/seller")
public class SellerController {

    @GetMapping
    public List<Seller> getSeller() {
        Location manizales = new Location("17001", "Manizales");
        Seller daniela = new Seller("1053854324", "Daniela", "Cifuentes Rendón", (char) 1, (byte) 28, manizales);
        Seller adriana = new Seller("30331751", "Adriana María", "Rendoón Gómez", (char) 1, (byte) 50, manizales);
        Seller andres = new Seller("1060649816", "Andrés", "Taborda Posada", (char) 2, (byte) 34, manizales);
        Seller bertha = new Seller("24316690", "Bertha Lucia", "Gómez Aguirre", (char) 1, (byte) 69, manizales);
        Seller valery = new Seller("1002810745", "Valery", "Velasquez Cifuentes", (char) 1, (byte) 12, manizales);

        return List.of(daniela, adriana, andres, bertha, valery);
    }
}
