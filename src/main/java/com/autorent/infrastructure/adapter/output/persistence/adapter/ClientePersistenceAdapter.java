package com.autorent.infrastructure.adapter.output.persistence.adapter;

import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.domain.model.Cliente;
import com.autorent.infrastructure.adapter.output.persistence.entity.ClienteEntity;
import com.autorent.infrastructure.adapter.output.persistence.repository.ClienteJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientePersistenceAdapter implements ClienteRepositoryPort {
    private final ClienteJpaRepository repo;
    public ClientePersistenceAdapter(ClienteJpaRepository repo) { this.repo = repo; }

    @Override
    public Cliente guardar(Cliente c) {
        ClienteEntity e = toEntity(c);
        return toDomain(repo.save(e));
    }
    @Override
    public Optional<Cliente> buscarPorId(Long id) { return repo.findById(id).map(this::toDomain); }
    @Override
    public Optional<Cliente> buscarPorDocumento(String documento) { return repo.findByDocumento(documento).map(this::toDomain); }
    @Override
    public List<Cliente> buscarTodos() { return repo.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private ClienteEntity toEntity(Cliente c) {
        ClienteEntity e = new ClienteEntity();
        e.setId(c.getId()); e.setNombre(c.getNombre()); e.setDocumento(c.getDocumento());
        e.setTelefono(c.getTelefono()); e.setEmail(c.getEmail());
        return e;
    }
    private Cliente toDomain(ClienteEntity e) {
        Cliente c = new Cliente();
        c.setId(e.getId()); c.setNombre(e.getNombre()); c.setDocumento(e.getDocumento());
        c.setTelefono(e.getTelefono()); c.setEmail(e.getEmail());
        return c;
    }
}