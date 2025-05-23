package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.dto.SaleDto;
import co.edu.umanizales.myfirstapi.model.Sale;
import co.edu.umanizales.myfirstapi.model.Store;
import co.edu.umanizales.myfirstapi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PutMapping("/add")
    public String addSale(@RequestBody SaleDto saleDto){
        return saleService.createSale(saleDto);
    }
}
