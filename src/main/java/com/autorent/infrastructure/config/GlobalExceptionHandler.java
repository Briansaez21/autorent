package com.autorent.infrastructure.config;

import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.exception.AutoNoDisponibleException;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.exception.ClienteYaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AutoNoEncontradoException.class, ClienteNoEncontradoException.class, AlquilerNoEncontradoException.class})
    public ResponseEntity<String> notFound(RuntimeException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); }

    @ExceptionHandler(AutoNoDisponibleException.class)
    public ResponseEntity<String> conflict(RuntimeException e) { return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); }

    @ExceptionHandler({ClienteYaExisteException.class, IllegalArgumentException.class})
    public ResponseEntity<String> badRequest(RuntimeException e) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); }
}