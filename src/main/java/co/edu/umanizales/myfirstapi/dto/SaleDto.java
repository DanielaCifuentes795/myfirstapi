package co.edu.umanizales.myfirstapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleDto {
    private String storeCode;
    private String sellerIdentification;
    private LocalDate dateSale;
    private List<String> productIds;
}
