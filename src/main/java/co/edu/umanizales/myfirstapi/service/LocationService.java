package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
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
            locations.add(new Location("05","ANTIOQUIA"));
            locations.add(new Location("17","CALDAS"));
            locations.add(new Location("66","RISARALDA"));
            locations.add(new Location("91","AMAZONAS"));
            locations.add(new Location("08","ATLANTICO"));
            locations.add(new Location("11","BOGOTA"));
            locations.add(new Location("13","BOLIVAR"));
            locations.add(new Location("15","BOYACA"));
            locations.add(new Location("18","CAQUETA"));
            locations.add(new Location("19","CAUCA"));
            locations.add(new Location("85","CASANARE"));
            locations.add(new Location("20","CESAR"));
            locations.add(new Location("27","CHOCO"));
            locations.add(new Location("25","CUNDINAMARCA"));
            locations.add(new Location("23","CORDOBA"));
            locations.add(new Location("94","GUANIA"));
            locations.add(new Location("95","GUAVIARE"));
            locations.add(new Location("41","HUILA"));
            locations.add(new Location("44","LA GUAJIRA"));
            locations.add(new Location("47","MAGDALENA"));
            locations.add(new Location("50","META"));
            locations.add(new Location("52","NARIÑO"));
            locations.add(new Location("54","NORTE DE SANTANDER"));
            locations.add(new Location("86","PUTUMAYO"));
            locations.add(new Location("63","QUINDIO"));
            locations.add(new Location("88","SAN ANDRES y PROVIDENCIA"));
            locations.add(new Location("68","SANTANDER"));
            locations.add(new Location("70","SUCRE"));
            locations.add(new Location("73","TOLIMA"));
            locations.add(new Location("76","VALLE DEL CAUCA"));
            locations.add(new Location("99","VICHADA"));
            locations.add(new Location("97","VAUPES"));
            locations.add(new Location("81","ARAUCA"));

            Path pathFile = Paths.get(ClassLoader.getSystemResource(locations_filename).toURI());

            try (CSVReader csvReader = new CSVReader(new FileReader(pathFile.toString()))) {
                String[] line;
                csvReader.skip(1);
                //Leer todas las filas del CSV
                while ((line = csvReader.readNext()) != null) {
                    System.out.println(line[1]);
                    //Crear un nuevo objeto Location y agregarlo a la lista
                    locations.add(new Location(line[2], line[3]));
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
        public List<Location> getLocationByStateCode(String stateCode) {
            List<Location> locationStateCode = new ArrayList<>();
            for (Location location : locations) {
                if (location.getCode().equals(stateCode)) {
                    locationStateCode.add(location);
                }
            }
            return locationStateCode;
        }

        //Objetivo: Crear un metodo que retorne la lista de municipios que coincidan con el codigo del departamento de Colombia al que pertenecen
        //Datos de entrada: Un numero de dos digitos
        //Datos de salida: Un listado de sitios con la informacion de los muninicipios que hacen parte del departamento bajo el codigo ingresado
        public List<Location> getLocationStateByCode(String code) {
             List<Location> locationCode = new ArrayList<>();
            for (Location location : locations) {
                if (location.getCode().equals(code)) {
                    locationCode.add(location);
                }
            }
            return locationCode;
        }

        //Objetivo: Crear un metodo que retorne la lista las ciudades capitales de Colombia
        //Datos de entrada: La palabra "Capitals"
        //Datos de salida: Listado de las ciudades capitales del país
        public List<Location> getLocationCapitals () {
            List<Location> locationCapitals = new ArrayList<>();
            for (Location location : locations) {
                if (location.getCode().contains("001") && location.getCode().length() == 5) {
                    locationCapitals.add(location);
                }
            }
            return locationCapitals;
        }

        //Objetivo: Crear un metodo que retorne la lista de departamentos de Colombia
        //Datos de entrada: La palabra "States"
        //Datos de salida: Listado de los departamentos del país
        public List<Location> getStates() {
            List<Location> locationStates = new ArrayList<>();
            for (Location location : locations) {
                if(location.getCode().length()==2){
                    locationStates.add(location);
                }
            }
            return locationStates;
        }

        //Objetivo: Crear un metodo que retorne una lista de municipios de Colombia que inicie y termine en dos letras ingresadas por el usuario
        //Datos de entrada: Las dos letras que el usuario desee ingresar
        //Datos de salida: Un listado de sitios con la informacion de los muninicipios que coincide con las letras ingresadas
        public List<Location> getLocationByLetters(String initial,String end) {
        List<Location> locationLetters = new ArrayList<>();
        for (Location location : locations) {
            if (location.getName().startsWith(initial) && location.getName().endsWith(end) ) {
                locationLetters.add(location);
            }
        }
        return locationLetters;
    }
}
