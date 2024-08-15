package com.api.reservas.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.dto.DependenciasUpdateDTO;
import com.api.reservas.demo.dto.DependenciasDTO;
import com.api.reservas.demo.service.DependenciasService;

@RestController
@RequestMapping("dependencias")
public class DependenciasController {

    @Autowired
    private DependenciasService dependenciasService;
    
    //buscar dependências
    @GetMapping
    public ResponseEntity<List<Dependencias>>getAll(){
        List<Dependencias> dependencias = dependenciasService.getAll();
        return ResponseEntity.ok(dependencias);
    }

    //dependências disponíveis
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Dependencias>> getAllDisponiveis() {
        List<Dependencias> dependencias = dependenciasService.getAllDisponiveis();
        return ResponseEntity.ok(dependencias);
    }
    
    // buscar dependências por id
    @GetMapping("/{id}")
    public ResponseEntity<Dependencias> getById(@PathVariable Long id) {
        Dependencias dependencias = dependenciasService.getById(id);
        if (dependencias == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependencias);
    }


    //criar dependências
    @PostMapping
    public ResponseEntity<Dependencias> create(@RequestBody Dependencias dependencias) {
      Dependencias dependenciaSalva = dependenciasService.create(dependencias);
       return ResponseEntity.ok(dependenciaSalva);
    }

    // Excluir dependências
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

            dependenciasService.delete(id);
            return ResponseEntity.noContent().build();
    }

     //Combinação do GetById com o create
    @PutMapping("/dto/{id}")
    public ResponseEntity<DependenciasDTO> updateDTO (@PathVariable Long id , @RequestBody DependenciasUpdateDTO dependenciasAtualizado) {
        Dependencias dependenciaExistente = dependenciasService.getById(id);
        
        if(dependenciaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        DependenciasDTO dependenciasDTO = dependenciasService.updateDTO(dependenciasAtualizado, dependenciaExistente);
            return ResponseEntity.ok(dependenciasDTO);
    }
    

    // Atualizar uma dependência
    @PutMapping("/{id}")
     public ResponseEntity<Dependencias> update(@PathVariable Long id, @RequestBody Dependencias dependencias){
        Dependencias dependenciasAtualizado = dependenciasService.update(id, dependencias);

        if (dependenciasAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependenciasAtualizado);
    }
  
}