package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.dto.SaleDto;
import co.edu.umanizales.myfirstapi.model.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class SaleService {
    private final StoreService storeService;
    private final SellerService sellerService;
    private final List<Sale> sales;
    private final ProductService productService;

    public SaleService(StoreService storeService, List<Sale> sales, SellerService sellerService, ProductService productService) {
        this.storeService = storeService;
        this.sales = sales;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public String createSale(SaleDto sale) {
        Store store = storeService.seachStore(sale.getStoreCode());
        Seller seller = sellerService.searchSeller(sale.getSellerIdentification());
        List<Product> products = new ArrayList<>();
        int quantity;
        double totalSale = 0;

        for(String productid: sale.getProductIds()) {
            if(productService.searchProduct(productid) != null) {
                System.out.println(productid);
                productService.reduceStockByCode(productid);
                products.add(productService.searchProduct(productid));
                totalSale += productService.searchProduct(productid).getPrice();
            } else {
                return "No existe el producto " + productid;
            }
        }
        if(store == null) {
            return "no existe la tienda";
        } else if (seller == null) {
            return "no existe el vendedor";
        } else if (products.isEmpty()) {
            return "no agrego productos a la venta";
        }
        quantity = products.size();
        sales.add(new Sale(store, seller, sale.getDateSale(),quantity, totalSale, products));
        return "Se creo la venta con un total de: " + totalSale;
    }


    public List<Sale> filterByStore(String storeCode) {
        List<Sale> salesByStore = new ArrayList<>();
        for(Sale sale: sales) {
            if(sale.getStore().getCode().equals(storeCode)) {
                salesByStore.add(sale);
            }
        }
        return salesByStore;
    }

    public List<Sale> filterBySeller(String identification) {
        List<Sale> salesBySeller = new ArrayList<>();
        for(Sale sale: sales) {
            if(sale.getSeller().getIdentification().equals(identification)) {
                salesBySeller.add(sale);
            }
        }
        return salesBySeller;
    }

    public List<Sale> filterByProduct(String productId) {
        List<Sale> salesByProduct = new ArrayList<>();
        for (Sale sale : sales) {
            for (Product product : sale.getProducts()) {
                if (product.getCode().equals(productId)) {
                    salesByProduct.add(sale);
                }
            }
        }
        return salesByProduct;
    }

    public List<Sale> filterByDay(LocalDate day) {
        List<Sale> salesByDate = new ArrayList<>();
        for (Sale sale : sales) {
            if (sale.getDateSale().equals(day)) {
                salesByDate.add(sale);
            }
        }
        return salesByDate;
    }
}