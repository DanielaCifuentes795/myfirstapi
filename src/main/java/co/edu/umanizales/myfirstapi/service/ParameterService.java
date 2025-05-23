package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParameterService {
    private List<Parameter> parameters;

    @PostConstruct
    public void loadParameters() {
        parameters = new ArrayList<>();
        //Leeria los diferentes CSV
        parameters.add(new TypeDocument("CC","Cédula de ciudadanía"));
        parameters.add(new TypeDocument("NIT", "Número de identificación tributaria"));

        //Type Product
        TypeProduct ropa = new TypeProduct("1","Camiseta blanca unisex");
        parameters.add(ropa);
        parameters.add(new TypeProduct("2","Pantalon jean mujer"));

        parameters.add(new Product("1","Camiseta blanca mujer",62000,100, ropa));
    }

    public List<Parameter> getParametersByType(int type) {
        List<Parameter> result = new ArrayList<>();
        for(Parameter p : parameters){
            switch (type){
                case 1:
                    if(p instanceof TypeDocument){
                        result.add(p);
                    }
                    break;
                case 2:
                    if(p instanceof TypeProduct){
                        result.add(p);
                    }
                    break;
                case 3:
                    if(p instanceof Product){
                        result.add(p);
                    }
                    break;
                case 4:
                default:
                    System.out.println("El tipo ingresado no existe");
                    return result;
            }
        }
        if (result.isEmpty()){
            System.out.println("No se encontró información para el tipo ingresado");

            }
        return result;
    }

    public TypeDocument getTypeDocument(String type) {

        for (Parameter p : parameters) {
            if (p.getCode().equals(type)) {
                return (TypeDocument) p;
            }
        }
        return null;
    }
}
