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
        Location pereira = new Location("66001", "Pereira");
        Location risaralda = new Location("17616", "Risaralda");
        Location aranzazu = new Location("17050", "Aranzazu");
        Seller daniela = new Seller("1053854324", "Daniela", "Cifuentes Rendón", 'F', (byte) 28, manizales);
        Seller adriana = new Seller("30331751", "Adriana María", "Rendoón Gómez", 'F', (byte) 50, manizales);
        Seller andres = new Seller("1060649816", "Andrés", "Taborda Posada", 'M', (byte) 34, risaralda);
        Seller bertha = new Seller("24316690", "Bertha Lucia", "Gómez Aguirre", 'F', (byte) 69, aranzazu);
        Seller valery = new Seller("1002810745", "Valery", "Velasquez Cifuentes", 'F', (byte) 12, pereira);

        return List.of(daniela, adriana, andres, bertha, valery);
    }
}
