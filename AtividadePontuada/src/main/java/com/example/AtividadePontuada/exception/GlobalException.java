package com.example.AtividadePontuada.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;


// Essa anotação é muito importante. Ela diz ao Spring que esta classe
// deve monitorar TODOS os Controllers em busca de erros.
@RestControllerAdvice
public class GlobalException {

    // Captura erros genéricos de tempo de execução.
    // Útil para erros de banco de dados ou problemas inesperados.
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException exception){
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // Retorna 409 Conflict.
                .body(Map.of("mensagem", exception.getMessage()));
    }

    // Captura erros de validação (como o "já cadastrado").
    // O @ExceptionHandler diz exatamente qual "bomba" esse método deve desarmar.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handlerIllegalArgumentException(IllegalArgumentException exception){
        // Retorna 400 Bad Request, limpando o stacktrace e deixando apenas a mensagem.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensagem", exception.getMessage()));
    }
}