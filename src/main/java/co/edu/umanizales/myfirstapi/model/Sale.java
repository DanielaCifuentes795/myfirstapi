package co.edu.umanizales.myfirstapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;

@Setter
@Getter
@AllArgsConstructor
public class Sale {
    private Store store;
//    private Seller seller;
//    private Locale dateSale;
//    private int quantity;
//    private List<Product> products;
}
