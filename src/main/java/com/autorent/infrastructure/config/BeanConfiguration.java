package com.autorent.infrastructure.config;

import com.autorent.application.port.input.*;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.application.service.*;
import com.autorent.infrastructure.adapter.output.persistence.adapter.AlquilerPersistenceAdapter;
import com.autorent.infrastructure.adapter.output.persistence.adapter.AutoPersistenceAdapter;
import com.autorent.infrastructure.adapter.output.persistence.adapter.ClientePersistenceAdapter;
import com.autorent.infrastructure.adapter.output.persistence.repository.AlquilerJpaRepository;
import com.autorent.infrastructure.adapter.output.persistence.repository.AutoJpaRepository;
import com.autorent.infrastructure.adapter.output.persistence.repository.ClienteJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean public AutoRepositoryPort autoRepositoryPort(AutoJpaRepository repo) { return new AutoPersistenceAdapter(repo); }
    @Bean public ClienteRepositoryPort clienteRepositoryPort(ClienteJpaRepository repo) { return new ClientePersistenceAdapter(repo); }
    @Bean public AlquilerRepositoryPort alquilerRepositoryPort(AlquilerJpaRepository repo) { return new AlquilerPersistenceAdapter(repo); }

    @Bean public RegistrarAutoUseCase registrarAutoUseCase(AutoRepositoryPort p) { return new RegistrarAutoUseCaseImpl(p); }
    @Bean public ConsultarAutoUseCase consultarAutoUseCase(AutoRepositoryPort p) { return new ConsultarAutoUseCaseImpl(p); }
    @Bean public ConsultarAutosDisponiblesUseCase consultarAutosDisponiblesUseCase(AutoRepositoryPort p) { return new ConsultarAutosDisponiblesUseCaseImpl(p); }

    @Bean public RegistrarClienteUseCase registrarClienteUseCase(ClienteRepositoryPort p) { return new RegistrarClienteUseCaseImpl(p); }
    @Bean public ConsultarClienteUseCase consultarClienteUseCase(ClienteRepositoryPort p) { return new ConsultarClienteUseCaseImpl(p); }

    @Bean public CrearAlquilerUseCase crearAlquilerUseCase(AlquilerRepositoryPort a, AutoRepositoryPort au, ClienteRepositoryPort c) { return new CrearAlquilerUseCaseImpl(a,au,c); }
    @Bean public ConsultarAlquilerUseCase consultarAlquilerUseCase(AlquilerRepositoryPort a) { return new ConsultarAlquilerUseCaseImpl(a); }
    @Bean public FinalizarAlquilerUseCase finalizarAlquilerUseCase(AlquilerRepositoryPort a, AutoRepositoryPort au) { return new FinalizarAlquilerUseCaseImpl(a,au); }
}
