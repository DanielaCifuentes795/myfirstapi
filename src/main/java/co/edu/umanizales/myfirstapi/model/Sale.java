package co.edu.umanizales.myfirstapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@AllArgsConstructor
public class Sale {
    private String nameCustomer;
    private String identification;
    private String numberPhone;
    private double price;
    private String product;
}
