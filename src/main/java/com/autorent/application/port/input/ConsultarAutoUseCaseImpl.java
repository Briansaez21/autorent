package com.autorent.application.port.input;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.service.ConsultarAutoUseCase;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.model.Auto;
public class ConsultarAutoUseCaseImpl implements ConsultarAutoUseCase {
    private final AutoRepositoryPort repo;
    public ConsultarAutoUseCaseImpl(AutoRepositoryPort repo){this.repo=repo;}
    public Auto consultar(Long id){ return repo.buscarPorId(id).orElseThrow(()->new AutoNoEncontradoException("Auto no encontrado con id: "+id));}
}
