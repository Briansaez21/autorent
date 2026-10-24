package com.autorent.infrastructure.adapter.output.persistence.adapter;

import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAuto;
import com.autorent.infrastructure.adapter.output.persistence.entity.AutoEntity;
import com.autorent.infrastructure.adapter.output.persistence.repository.AutoJpaRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AutoPersistenceAdapter implements AutoRepositoryPort {

    private final AutoJpaRepository repo;

    public AutoPersistenceAdapter(AutoJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Auto guardar(Auto auto) {
        AutoEntity e = toEntity(auto);
        AutoEntity saved = repo.save(e);
        return toDomain(saved);
    }

    @Override
    public Optional<Auto> buscarPorId(Long id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Auto> buscarDisponibles() {
        return repo.findAll().stream()
                .filter(e -> "DISPONIBLE".equals(e.getEstado()))
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Auto> buscarTodos() {
        return repo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    private AutoEntity toEntity(Auto a) {
        AutoEntity e = new AutoEntity();
        e.setId(a.getId());
        e.setPlaca(a.getPlaca());
        e.setMarca(a.getMarca());
        e.setModelo(a.getModelo());
        e.setAnio(a.getAnio());
        e.setPrecioPorDia(a.getPrecioPorDia());
        e.setEstado(a.getEstado() == null ? null : a.getEstado().name());
        return e;
    }

    private Auto toDomain(AutoEntity e) {
        Auto a = new Auto();
        a.setId(e.getId());
        a.setPlaca(e.getPlaca());
        a.setMarca(e.getMarca());
        a.setModelo(e.getModelo());
        a.setAnio(e.getAnio());
        a.setPrecioPorDia(e.getPrecioPorDia());
        a.setEstado(e.getEstado() == null ? null : EstadoAuto.valueOf(e.getEstado()));
        return a;
    }
}