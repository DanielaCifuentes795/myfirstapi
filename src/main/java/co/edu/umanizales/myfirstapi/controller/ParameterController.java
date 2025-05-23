package co.edu.umanizales.myfirstapi.controller;


import co.edu.umanizales.myfirstapi.model.Parameter;
import co.edu.umanizales.myfirstapi.model.Product;
import co.edu.umanizales.myfirstapi.model.TypeDocument;
import co.edu.umanizales.myfirstapi.model.TypeProduct;
import co.edu.umanizales.myfirstapi.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/parameter")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @GetMapping(path="/typedocuments")
    public List<Parameter> getTypeDocuments(){
        return parameterService.getParametersByType(1);
    }

    @GetMapping(path = "/typeproducts")
    public List<Parameter> getTypeProduct(){
        return parameterService.getParametersByType(2);
    }

    @GetMapping(path = "/products")
    public List<Parameter> getProducts(){
        return parameterService.getParametersByType(3);
    }
}
