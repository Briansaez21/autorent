package com.autorent.application.port.input;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.application.service.ConsultarClienteUseCase;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.model.Cliente;
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {
    private final ClienteRepositoryPort repo;
    public ConsultarClienteUseCaseImpl(ClienteRepositoryPort repo){this.repo=repo;}
    public Cliente consultar(Long id){ return repo.buscarPorId(id).orElseThrow(()->new ClienteNoEncontradoException("Cliente no encontrado con id: "+id));}
}
