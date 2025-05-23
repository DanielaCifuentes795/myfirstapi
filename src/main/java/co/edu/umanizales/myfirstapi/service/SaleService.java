package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.dto.SaleDto;
import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Sale;
import co.edu.umanizales.myfirstapi.model.Store;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter

public class SaleService {
    private final StoreService storeService;
    private List<Sale> sales;

    public SaleService(StoreService storeService, List<Sale> sales) {
        this.storeService = storeService;
        this.sales = sales;
    }

    public String createSale(SaleDto sale) {
         Store store = storeService.seachStore(sale.getStoreCode());
        sales.add(new Sale(store));
         return "Se creo la venta";
    }
}
