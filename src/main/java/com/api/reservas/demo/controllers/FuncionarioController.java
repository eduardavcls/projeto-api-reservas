package com.api.reservas.demo.controllers;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.reservas.demo.classes.Funcionario;
import com.api.reservas.demo.service.FuncionarioService;

@RestController
@RequestMapping("funcionarios")
@Tag(name = "Funcionários", description = "Endpoints para gerenciar funcionários")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(
            summary = "Busca todos os funcionários",
            description = "Obtém uma lista de todos os funcionários cadastrados no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de funcionários retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    @GetMapping
    public ResponseEntity<List<Funcionario>> getAll() {
        List<Funcionario> funcionarios = funcionarioService.getAll();
        return ResponseEntity.ok(funcionarios);
    }

    @Operation(
            summary = "Busca um funcionário por ID",
            description = "Obtém um funcionário específico com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID do funcionário a ser buscado", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.getById(id);
        if (funcionario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionario);
    }

    @Operation(
            summary = "Cria um novo funcionário",
            description = "Adiciona um novo funcionário ao sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
            }
    )
    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioService.create(funcionario);
        return ResponseEntity.ok(funcionarioSalvo);
    }

    @Operation(
            summary = "Exclui um funcionário por ID",
            description = "Remove um funcionário existente com base no ID fornecido.",
            parameters = {
                    @Parameter(name = "id", description = "ID do funcionário a ser excluído", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> delete(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.getById(id);
        if (funcionario == null) {
            return ResponseEntity.notFound().build();
        }
        funcionarioService.delete(id);
        return ResponseEntity.ok(funcionario);
    }

    @Operation(
            summary = "Busca todos os funcionários ativos",
            description = "Obtém uma lista de funcionários que estão ativos no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de funcionários ativos retornada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
            }
    )
    @GetMapping("/ativos")
    public ResponseEntity<List<Funcionario>> getAllAtivos() {
        List<Funcionario> funcionarios = funcionarioService.getAllAtivos();
        return ResponseEntity.ok(funcionarios);
    }
}
