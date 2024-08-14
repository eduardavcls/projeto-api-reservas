package com.api.reservas.demo.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.service.DependenciasService;

@RestController
@RequestMapping("dependencias")


public class DependenciasController {

    @GetMapping
    public ResponseEntity<List<Dependencias>>getAll();{
        List<Dependencias>dependencias = DependenciasService.getAll();
    }
        

  
}
