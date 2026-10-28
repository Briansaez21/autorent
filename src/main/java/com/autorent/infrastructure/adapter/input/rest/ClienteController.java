package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.application.port.input.ConsultarClienteUseCase;
import com.autorent.application.port.input.RegistrarClienteUseCase;
import com.autorent.domain.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final RegistrarClienteUseCase registrar;
    private final ConsultarClienteUseCase consultar;

    public ClienteController(RegistrarClienteUseCase registrar, ConsultarClienteUseCase consultar) {
        this.registrar = registrar; this.consultar = consultar;
    }

    public record ClienteRequest(String nombre, String documento, String telefono, String email) {}
    public record ClienteResponse(Long id, String nombre, String documento, String telefono, String email) {}

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest req) {
        Cliente c = registrar.registrar(req.nombre(), req.documento(), req.telefono(), req.email());
        return ResponseEntity.ok(new ClienteResponse(c.getId(), c.getNombre(), c.getDocumento(), c.getTelefono(), c.getEmail()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> consultar(@PathVariable Long id) {
        Cliente c = consultar.consultar(id);
        return ResponseEntity.ok(new ClienteResponse(c.getId(), c.getNombre(), c.getDocumento(), c.getTelefono(), c.getEmail()));
    }
}
