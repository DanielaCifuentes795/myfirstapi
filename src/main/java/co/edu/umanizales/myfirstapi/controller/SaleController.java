package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.dto.SaleDto;
import co.edu.umanizales.myfirstapi.model.Sale;
import co.edu.umanizales.myfirstapi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @PutMapping("/add")
    public String addSale(@RequestBody SaleDto saleDto){
        return saleService.createSale(saleDto);
    }

    @GetMapping(path = "/filter-store/{code}")
    public List<Sale> getByStore(@PathVariable String code) {
        return saleService.filterByStore(code);
    }

    @GetMapping(path = "/filter-seller/{identification}")
    public List<Sale> getBySeller(@PathVariable String identification) {
        return saleService.filterBySeller(identification);
    }

    @GetMapping(path = "/filter-product/{productId}")
    public List<Sale> getByProduct(@PathVariable String productId) {
        return saleService.filterByProduct(productId);
    }

    @GetMapping(path = "/filter-day/{day}")
    public List<Sale> getByProduct(@PathVariable LocalDate day) {
        return saleService.filterByDay(day);
    }
}