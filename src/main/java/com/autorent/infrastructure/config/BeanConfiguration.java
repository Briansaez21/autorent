package com.autorent.infrastructure.config;
import com.autorent.application.port.input.*;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.application.service.AlquilerService;
import com.autorent.application.service.AutoService;
import com.autorent.application.service.ClienteService;
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
    @Bean public AutoRepositoryPort autoRepositoryPort(AutoJpaRepository repo){return new AutoPersistenceAdapter(repo);}
    @Bean public ClienteRepositoryPort clienteRepositoryPort(ClienteJpaRepository repo){return new ClientePersistenceAdapter(repo);}
    @Bean public AlquilerRepositoryPort alquilerRepositoryPort(AlquilerJpaRepository repo){return new AlquilerPersistenceAdapter(repo);}
    @Bean public AutoService autoService(AutoRepositoryPort p){return new AutoService(p);}
    @Bean public ClienteService clienteService(ClienteRepositoryPort p){return new ClienteService(p);}
    @Bean public AlquilerService alquilerService(AlquilerRepositoryPort a,AutoRepositoryPort au,ClienteRepositoryPort c){return new AlquilerService(a,au,c);}
}
