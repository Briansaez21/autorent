package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.application.service.ConsultarAlquilerUseCase;
import com.autorent.application.service.CrearAlquilerUseCase;
import com.autorent.application.service.FinalizarAlquilerUseCase;
import com.autorent.domain.model.Alquiler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {
    private final CrearAlquilerUseCase crear;
    private final ConsultarAlquilerUseCase consultar;
    private final FinalizarAlquilerUseCase finalizar;

    public AlquilerController(CrearAlquilerUseCase crear, ConsultarAlquilerUseCase consultar, FinalizarAlquilerUseCase finalizar) {
        this.crear = crear; this.consultar = consultar; this.finalizar = finalizar;
    }

    public record AlquilerRequest(Long clienteId, Long autoId, Integer cantidadDias) {}
    public record AlquilerResponse(Long id, Long clienteId, Long autoId, String fechaInicio, String fechaFin, Integer cantidadDias, BigDecimal valorTotal, String estado) {}

    @PostMapping
    public ResponseEntity<AlquilerResponse> crear(@RequestBody AlquilerRequest req) {
        Alquiler a = crear.crear(req.clienteId(), req.autoId(), req.cantidadDias());
        return ResponseEntity.ok(toResponse(a));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlquilerResponse> consultar(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(consultar.consultar(id)));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<AlquilerResponse> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(finalizar.finalizar(id)));
    }

    private AlquilerResponse toResponse(Alquiler a) {
        return new AlquilerResponse(a.getId(), a.getClienteId(), a.getAutoId(),
                a.getFechaInicio() == null ? null : a.getFechaInicio().toString(),
                a.getFechaFin() == null ? null : a.getFechaFin().toString(),
                a.getCantidadDias(), a.getValorTotal(),
                a.getEstado() == null ? null : a.getEstado().name());
    }
}
