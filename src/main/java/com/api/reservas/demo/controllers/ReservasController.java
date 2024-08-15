package com.api.reservas.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.classes.Reservas;
import com.api.reservas.demo.dto.ReservaDTO;
import com.api.reservas.demo.service.DependenciasService;
import com.api.reservas.demo.service.ReservasService;

@RestController
@RequestMapping("reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    // buscar reservas
    @GetMapping
    public ResponseEntity<List<Reservas>> getAll() {
        List<Reservas> reservas = reservasService.getAll();
        return ResponseEntity.ok(reservas);
    }

    // buscar reservas por id
    @GetMapping("/{id}")
    public ResponseEntity<Reservas> getById(@PathVariable Long id) {
        Reservas reservas = reservasService.getById(id);
        if (reservas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    // criar Reservas
    @PostMapping
    public ResponseEntity<ReservaDTO> create(@RequestBody ReservaDTO reserva) {
        ReservaDTO reservaSalva = reservasService.create(reserva);
        return ResponseEntity.ok(reservaSalva);
    }

    // Desativar reservas
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Reservas reservas = reservasService.getById(id);

        if (reservas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservasService.delete(id));
    }
}
