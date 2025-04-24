package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.State;
import co.edu.umanizales.myfirstapi.model.Store;
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
public class StoreService {

    private final LocationService locationService;
    private List<Store> store;

    @Value("${store_filename}")
    private String store_filename;

    public StoreService(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostConstruct
    public void readStoreFromCSV() throws IOException, URISyntaxException {
        store = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(store_filename).toURI());

        try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
            String[] line;
            csvReader.skip(1);
            //Leer todas las filas del CSV
            while ((line = csvReader.readNext()) != null) {
                System.out.println(line[1]);
                Location city = locationService.getLocationByName(line[3]);
                //Crear un nuevo objeto Store y agregarlo a la lista
                store.add(new Store(line[0],line[1],line[2],city));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e; //Lanza la excepción para que pueda manejarse en la capa superior si es necesario
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
