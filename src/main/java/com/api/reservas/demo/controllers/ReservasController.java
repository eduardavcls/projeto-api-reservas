package com.api.reservas.demo.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.reservas.demo.classes.Reservas;
import com.api.reservas.demo.dto.ReservaDTO;
import com.api.reservas.demo.service.ReservasService;

@RestController
@RequestMapping("reservas")
@Tag(name = "Reservas", description = "Endpoints para gerenciar reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @Operation(
            summary = "Busca todas as reservas",
            description = "Obtém uma lista de todas as reservas cadastradas no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de reservas retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    @GetMapping
    public ResponseEntity<List<Reservas>> getAll() {
        List<Reservas> reservas = reservasService.getAll();
        return ResponseEntity.ok(reservas);
    }

    @Operation(
            summary = "Busca uma reserva por ID",
            description = "Obtém uma reserva específica com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID da reserva a ser buscada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reserva encontrada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Reservas> getById(@PathVariable Long id) {
        Reservas reservas = reservasService.getById(id);
        if (reservas == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @Operation(
            summary = "Cria uma nova reserva",
            description = "Adiciona uma nova reserva ao sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reserva criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            }
    )
    @PostMapping
    public ResponseEntity<ReservaDTO> create(@RequestBody ReservaDTO reserva) {
        ReservaDTO reservaSalva = reservasService.create(reserva);
        return ResponseEntity.ok(reservaSalva);
    }

    @Operation(
            summary = "Desativa uma reserva por ID",
            description = "Remove (desativa) uma reserva existente com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID da reserva a ser desativada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reserva desativada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Reserva não encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Reservas reservas = reservasService.getById(id);

        if (reservas == null) {
            return ResponseEntity.notFound().build();
        }
        reservasService.delete(id);
        return ResponseEntity.ok().build();
    }
}
