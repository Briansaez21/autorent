package com.autorent.application.port.input;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.service.ConsultarAlquilerUseCase;
import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.model.Alquiler;
public class ConsultarAlquilerUseCaseImpl implements ConsultarAlquilerUseCase {
    private final AlquilerRepositoryPort repo;
    public ConsultarAlquilerUseCaseImpl(AlquilerRepositoryPort repo){this.repo=repo;}
    public Alquiler consultar(Long id){ return repo.buscarPorId(id).orElseThrow(()->new AlquilerNoEncontradoException("Alquiler no encontrado con id: "+id));}
}
