package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    public List<Location> readLocationFromCSV(String csvFilePath) throws IOException {
        List<Location> locations = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))){
            String[] line;
            //Leer todas las filas del CSV
            while ((line = csvReader.readNext()) != null) {
                String code = line[0];
                String description = line[1];

                Location location = new Location(code, description);
                //Location location = new Location("1","Manizales");
                locations.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return locations;
    }
}
