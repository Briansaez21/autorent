package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.application.port.input.ConsultarAutoUseCase;
import com.autorent.application.port.input.ConsultarAutosDisponiblesUseCase;
import com.autorent.application.port.input.RegistrarAutoUseCase;
import com.autorent.domain.model.Auto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutoController {
    private final RegistrarAutoUseCase registrar;
    private final ConsultarAutoUseCase consultar;
    private final ConsultarAutosDisponiblesUseCase disponibles;

    public AutoController(RegistrarAutoUseCase registrar, ConsultarAutoUseCase consultar, ConsultarAutosDisponiblesUseCase disponibles) {
        this.registrar = registrar; this.consultar = consultar; this.disponibles = disponibles;
    }

    public record AutoRequest(String placa, String marca, String modelo, Integer anio, BigDecimal precioPorDia) {}
    public record AutoResponse(Long id, String placa, String marca, String modelo, Integer anio, BigDecimal precioPorDia, String estado) {}

    @PostMapping
    public ResponseEntity<AutoResponse> crear(@RequestBody AutoRequest req) {
        Auto a = registrar.registrar(req.placa(), req.marca(), req.modelo(), req.anio(), req.precioPorDia());
        return ResponseEntity.ok(toResponse(a));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutoResponse> consultar(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(consultar.consultar(id)));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<AutoResponse>> disponibles() {
        return ResponseEntity.ok(disponibles.consultarDisponibles().stream().map(this::toResponse).toList());
    }

    private AutoResponse toResponse(Auto a) {
        return new AutoResponse(a.getId(), a.getPlaca(), a.getMarca(), a.getModelo(), a.getAnio(), a.getPrecioPorDia(), a.getEstado() == null ? null : a.getEstado().name());
    }
}
