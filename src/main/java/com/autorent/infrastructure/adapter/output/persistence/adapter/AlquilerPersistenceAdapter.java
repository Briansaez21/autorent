package com.autorent.infrastructure.adapter.output.persistence.adapter;

import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.domain.model.Alquiler;
import com.autorent.domain.model.EstadoAlquiler;
import com.autorent.infrastructure.adapter.output.persistence.entity.AlquilerEntity;
import com.autorent.infrastructure.adapter.output.persistence.repository.AlquilerJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AlquilerPersistenceAdapter implements AlquilerRepositoryPort {
    private final AlquilerJpaRepository repo;
    public AlquilerPersistenceAdapter(AlquilerJpaRepository repo) { this.repo = repo; }

    @Override
    public Alquiler guardar(Alquiler a) {
        AlquilerEntity e = toEntity(a);
        return toDomain(repo.save(e));
    }
    @Override
    public Optional<Alquiler> buscarPorId(Long id) { return repo.findById(id).map(this::toDomain); }
    @Override
    public List<Alquiler> buscarTodos() { return repo.findAll().stream().map(this::toDomain).collect(Collectors.toList()); }

    private AlquilerEntity toEntity(Alquiler a) {
        AlquilerEntity e = new AlquilerEntity();
        e.setId(a.getId()); e.setClienteId(a.getClienteId()); e.setAutoId(a.getAutoId());
        e.setFechaInicio(a.getFechaInicio()); e.setFechaFin(a.getFechaFin());
        e.setCantidadDias(a.getCantidadDias()); e.setValorTotal(a.getValorTotal());
        e.setEstado(a.getEstado() == null ? null : a.getEstado().name());
        return e;
    }
    private Alquiler toDomain(AlquilerEntity e) {
        Alquiler a = new Alquiler();
        a.setId(e.getId()); a.setClienteId(e.getClienteId()); a.setAutoId(e.getAutoId());
        a.setFechaInicio(e.getFechaInicio()); a.setFechaFin(e.getFechaFin());
        a.setCantidadDias(e.getCantidadDias()); a.setValorTotal(e.getValorTotal());
        a.setEstado(e.getEstado() == null ? null : EstadoAlquiler.valueOf(e.getEstado()));
        return a;
    }
}