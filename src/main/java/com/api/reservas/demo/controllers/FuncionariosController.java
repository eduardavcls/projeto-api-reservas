package com.api.reservas.demo.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.classes.Funcionarios;
import com.api.reservas.demo.service.DependenciasService;
import com.api.reservas.demo.service.FuncionariosService;


@RestController
@RequestMapping("funcionarios")
public class FuncionariosController {

     @Autowired
    private FuncionariosService funcionariosService;

    //buscar funcionarios
    @GetMapping
    public ResponseEntity<List<Funcionarios>> getAll() {
      List<Funcionarios> funcionarios = funcionariosService.getAll();
      return ResponseEntity.ok(funcionarios);
    }

    // buscar funcionários por id
     @GetMapping("/{id}")
     public ResponseEntity<Funcionarios> getById(@PathVariable Long id) {
        Funcionarios funcionarios = funcionariosService.getById(id);
         if (funcionarios == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(funcionarios);
     }
   
    //criar funcionários
    @PostMapping
    public ResponseEntity<Funcionarios> create(@RequestBody Funcionarios funcionarios) {
        Funcionarios funcionarioSalvo = funcionariosService.create(funcionarios);
       return ResponseEntity.ok(funcionarioSalvo);
    }

    // Excluir funcionários
     @DeleteMapping("/{id}")
     public ResponseEntity<Funcionarios> delete(@PathVariable Long id) {
     Funcionarios funcionarios = funcionariosService.getById(id);

       if (funcionarios == null) {
          return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(funcionariosService.delete(id));
     }
     
     //funcionários ativos
     @GetMapping("/ativos")
     public ResponseEntity<List<Funcionarios>> getAllAtivos() {
         List<Funcionarios> funcionarios = funcionariosService.getAllAtivos();
         return ResponseEntity.ok(funcionarios);
     }

}
