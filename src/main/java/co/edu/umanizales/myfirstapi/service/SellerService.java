package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.Seller;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
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
public class SellerService {
    private final ParameterService parameterService;
    private final LocationService locationService;
    private List<Seller> sellers;

    @Value("${seller_filename}")
    private String seller_filename;
    public SellerService(LocationService locationService, ParameterService parameterService) {
        this.locationService = locationService;
        this.parameterService = parameterService;

    }

    @PostConstruct
    public void readSellerFromCSV() throws IOException, URISyntaxException {
        sellers = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(seller_filename).toURI());

        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            //Leer todas las filas del CSV
            while ((line = csvReader.readNext()) != null) {
                TypeDocument document = parameterService.getTypeDocument(String.valueOf(line[1]));
                System.out.println(document);

                Location city = locationService.getLocationByName(line[5]);
                byte age = Byte.parseByte(line[4]);
                sellers.add(new Seller(line[0], document, line[2], line[3], age, city));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e; //Lanza la excepción para que pueda manejarse en la capa superior si es necesario
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
