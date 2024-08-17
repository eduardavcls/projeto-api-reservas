package com.api.reservas.demo.exception;

import org.hibernate.validator.constraints.EAN;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data não permitida");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        for (var error : ex.getBindingResult().getFieldErrors()) {

            // Verifica se o erro é relacionado ao campo "inicio" ou "fim"
            if (error.getField().equals("inicio") || error.getField().equals("fim")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data Invalida");
            }
        }
        // Se não for relacionado a data, retorna uma mensagem genérica
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo preenchido incorretamente");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O id deve ser um número inteiro");
    }
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String>handleNullPointerException(NullPointerException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("O Yuri usou o sistema contra o próprio sistema");
    }
}
