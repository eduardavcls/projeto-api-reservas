package com.api.reservas.demo.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.reservas.demo.classes.Dependencias;
import com.api.reservas.demo.dto.DependenciasDTO;
import com.api.reservas.demo.service.DependenciasService;

@RestController
@RequestMapping("dependencias")
@Tag(name = "Dependências", description = "Endpoints para gerenciar dependências")
public class DependenciasController {

    @Autowired
    private DependenciasService dependenciasService;

    @Operation(
            summary = "Busca todas as dependências",
            description = "Obtém uma lista de todas as dependências cadastradas no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de dependências retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    @GetMapping
    public ResponseEntity<List<Dependencias>> getAll() {
        List<Dependencias> dependencias = dependenciasService.getAll();
        return ResponseEntity.ok(dependencias);
    }

    @Operation(
            summary = "Busca todas as dependências disponíveis",
            description = "Obtém uma lista de dependências que estão disponíveis no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de dependências disponíveis retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    @GetMapping("/disponiveis")
    public ResponseEntity<List<Dependencias>> getAllDisponiveis() {
        List<Dependencias> dependencias = dependenciasService.getAllDisponiveis();
        return ResponseEntity.ok(dependencias);
    }

    @Operation(
            summary = "Busca uma dependência por ID",
            description = "Obtém uma dependência específica com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID da dependência a ser buscada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dependência encontrada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Dependência não encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Dependencias> getById(@PathVariable Long id) {
        Dependencias dependencias = dependenciasService.getById(id);
        if (dependencias == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependencias);
    }

    @Operation(
            summary = "Cria uma nova dependência",
            description = "Adiciona uma nova dependência ao sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dependência criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            }
    )
    @PostMapping
    public ResponseEntity<Dependencias> create(@RequestBody Dependencias dependencias) {
        Dependencias dependenciaSalva = dependenciasService.create(dependencias);
        return ResponseEntity.ok(dependenciaSalva);
    }

    @Operation(
            summary = "Exclui uma dependência por ID",
            description = "Remove uma dependência existente com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID da dependência a ser excluída", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Dependência excluída com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Dependência não encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dependenciasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualiza uma dependência",
            description = "Atualiza os dados de uma dependência existente com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID da dependência a ser atualizada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Dependência atualizada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Dependência não encontrada")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<DependenciasDTO> updateDependencia(@PathVariable Long id, @RequestBody DependenciasDTO dependenciasDTO) {
        DependenciasDTO updatedDependencia = dependenciasService.updateDTO(id, dependenciasDTO);

        if (updatedDependencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDependencia);
    }
}