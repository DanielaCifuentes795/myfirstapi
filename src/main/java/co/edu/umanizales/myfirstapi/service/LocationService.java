package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.model.State;
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
public class LocationService {

        private List<Location> locations;

        @Value("${locations_filename}")
        private String locations_filename;

        @PostConstruct
        public void readLocationsFromCSV() throws IOException, URISyntaxException {
            locations = new ArrayList<>();
    //        locations.add(new Location("05", "ANTIOQUIA"));
    //        locations.add(new Location("17", "CALDAS"));
    //        locations.add(new Location("66", "RISARALDA"));

            Path pathFile = Paths.get(ClassLoader.getSystemResource(locations_filename).toURI());

            try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
                String[] line;
                csvReader.skip(1);
                //Leer todas las filas del CSV
                while ((line = csvReader.readNext()) != null) {
                    System.out.println(line[1]);
                    //Crear un nuevo objeto Location y agregarlo a la lista
                    locations.add(new Location(line[0], line[1], line[2], line[3]));
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw e; //Lanza la excepción para que pueda manejarse en la capa superior si es necesario
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }

        //Objetivo: Crear un metodo que retorne el nombre y la información de un municipio de Colombia al ingresar un codigo
        //Datos de entrada: Un codigo que es conocido por el usuario
        //Datos de salida: Informacion del municipio al cual corresponde el codigo ingresado. Si no existe información, se retornará Null
        public Location getLocationByCode(String code) {
            for (Location location : locations) {
                if (location.getCode().equals(code)) {
                    return location;
                }
            }
            return null;
        }

        //Objetivo: Crear un metodo que retorne la información de un municipio de Colombia al ingresar el nombre
        //Datos de entrada: Nombre del municipio que se desea consultar
        //Datos de salida: Informacion del municipio al cual corresponde el nombre ingresado. Si no existe información, se retornará Null
        public Location getLocationByName(String name) {
            for (Location location : locations) {
                if (location.getName().equals(name)) {
                    return location;
                }
            }
            return null;
        }

        //Objetivo: Crear un metodo que retorne una lista de municipios de Colombia cuya letra inicial coincida con la ingresada por el usuario
        //Datos de entrada: La letra que el usuario desee ingresar
        //Datos de salida: Un listado de sitios con la informacion de los muninicipios que coincide con la letra ingresada
        public List<Location> getLocationsByInitialLetter(String letter) {
            List<Location> locationLetter = new ArrayList<>();
            for (Location location : locations) {
                if (location.getName().startsWith(letter)) {
                    locationLetter.add(location);
                }
            }
            return locationLetter;
        }

        //Objetivo: Crear un metodo que retorne la lista de municipios que coincidan con el codigo del departamento de Colombia al que pertenecen
        //Datos de entrada: Un numero de dos digitos
        //Datos de salida: Un listado de sitios con la informacion de los muninicipios que hacen parte del departmento bajo el codigo ingresado
        public List<Location> getLocationByStateCode(String statecode) {
            List<Location> locationStateCode = new ArrayList<>();
            for (Location location : locations) {
                if (location.getStateCode().equals(statecode)) {
                    locationStateCode.add(location);
                }
            }
            return locationStateCode;
        }

        //Objetivo: Crear un metodo que retorne la lista de municipios que coincidan con el codigo del departamento de Colombia al que pertenecen
        //Datos de entrada: Un numero de dos digitos
        //Datos de salida: Un listado de sitios con la informacion de los muninicipios que hacen parte del departamento bajo el codigo ingresado
        public State getLocationStateByCode(String code) {
            for (Location location : locations) {
                if (location.getStateCode().equals(code)) {
                    return new State(location.getStateCode(), location.getStateName());
                }
            }
            return null;
        }

        //Objetivo: Crear un metodo que retorne la lista las ciudades capitales de Colombia
        //Datos de entrada: La palabra "Capitals"
        //Datos de salida: Listado de las ciudades capitales del país
        public List<Location> getLocationCapitals () {
            List<Location> locationCapitals = new ArrayList<>();
            for (Location location : locations) {
                if (location.getCode().contains("001")) {
                    locationCapitals.add(location);
                }
            }
            return locationCapitals;
        }

        //Objetivo: Crear un metodo que retorne la lista de departamentos de Colombia
        //Datos de entrada: La palabra "States"
        //Datos de salida: Listado de los departamentos del país
        public List<State> getLocationStates() {
            List<State> locationStates = new ArrayList<>();
            String stateName = "";
            for (Location location : locations) {
                if(stateName.equals(location.getStateName())) {
                } else {
                    stateName = location.getStateName();
                    locationStates.add(new State(location.getStateCode(), location.getStateName()));
                }
            }
            return locationStates;
        }
}
