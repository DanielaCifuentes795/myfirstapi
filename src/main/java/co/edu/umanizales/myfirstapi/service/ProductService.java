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

    private final LocationService locationService;
    private List<Product> products;

    @Value("${product_filename}")
    private String product_filename;

    public ProductService(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostConstruct
    public void readProductFromCSV() throws IOException, URISyntaxException {
        products = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(product_filename).toURI());

        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            //Leer todas las filas del CSV
            while ((line = csvReader.readNext()) != null) {
                System.out.println(line[1]);
                Product price = new Product(line[2]);
                Product stock = new Product(line[3]);
                Product type = new TypeProduct(line[4]);
                //Crear un nuevo objeto Store y agregarlo a la lista
                products.add(new Product(line[0],line[1],price,stock,type));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e; //Lanza la excepción para que pueda manejarse en la capa superior si es necesario
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public Product seachProduct(String code) {
        for (Product product : products) {
            if (products.getCode().equals(code)) {
                return products;
            }
        }
        return null;
    }
}