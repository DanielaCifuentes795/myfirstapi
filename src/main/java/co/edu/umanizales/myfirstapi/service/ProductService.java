package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class ProductService {

    private final ParameterService parameterService;
    private List<Product> products;

    @Value("${product_filename}")
    private String product_filename;

    public ProductService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @PostConstruct
    public void readStoreFromCSV() throws IOException, URISyntaxException {
        products = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(product_filename).toURI());

        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            //Leer todas las filas del CSV
            while ((line = csvReader.readNext()) != null) {
                TypeProduct productType = parameterService.getTypeProduct(String.valueOf(line[4]));
                double price = Double.parseDouble(line[2]);
                int stock = Integer.parseInt(line[3]);
                products.add(new Product(line[0],line[1],price,stock,productType));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e; //Lanza la excepción para que pueda manejarse en la capa superior si es necesario
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public Product searchProduct(String productid) {
        for (Product product : products) {
            if (product.getCode().equals(productid)) {
                return product;
            }
        }
        return null;
    }

    public void reduceStockByCode(String productid) {
        for (Product p : products) {
            if (p.getCode().equals(productid)) {
                if (p.getStock() > 0) {
                    p.setStock(p.getStock() - 1);
                }
            }
        }
    }
}