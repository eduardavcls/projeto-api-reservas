package com.api.reservas.demo.controllers;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.reservas.demo.classes.Dependencia;
import com.api.reservas.demo.dto.DependenciaDTO;
import com.api.reservas.demo.service.DependenciaService;

@RestController
@RequestMapping("dependencias")
@Tag(name = "Dependências", description = "Endpoints para gerenciar dependências")
public class DependenciaController {

    @Autowired
    private DependenciaService dependenciaService;

    @Operation(
            summary = "Busca todas as dependências",
            description = "Obtém uma lista de todas as dependências cadastradas no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de dependências retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    @GetMapping
    public ResponseEntity<List<Dependencia>> getAll() {
        List<Dependencia> dependencias = dependenciaService.getAll();
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
    public ResponseEntity<Dependencia> getById(@PathVariable Long id) {
        Dependencia dependencia = dependenciaService.getById(id);
        if (dependencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependencia);
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
    public ResponseEntity<DependenciaDTO> create(@RequestBody DependenciaDTO dependenciaDTO) {
        DependenciaDTO dependenciaSalvaDTO = dependenciaService.create(dependenciaDTO);
        return ResponseEntity.ok(dependenciaSalvaDTO);
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
        dependenciaService.delete(id);
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
    public ResponseEntity<DependenciaDTO> update(@PathVariable Long id, @RequestBody DependenciaDTO dependenciaDTO) {
        DependenciaDTO dependenciaAtualizadoDTO = dependenciaService.update(id, dependenciaDTO);

        if (dependenciaAtualizadoDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependenciaAtualizadoDTO);
    }
}