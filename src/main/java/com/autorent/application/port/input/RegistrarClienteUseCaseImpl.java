package com.autorent.application.port.input;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.application.service.RegistrarClienteUseCase;
import com.autorent.domain.exception.ClienteYaExisteException;
import com.autorent.domain.model.Cliente;
public class RegistrarClienteUseCaseImpl implements RegistrarClienteUseCase {
    private final ClienteRepositoryPort repo;
    public RegistrarClienteUseCaseImpl(ClienteRepositoryPort repo){this.repo=repo;}
    public Cliente registrar(String nombre,String documento,String telefono,String email){
        if(repo.buscarPorDocumento(documento).isPresent()) throw new ClienteYaExisteException("Ya existe un cliente con documento: "+documento);
        return repo.guardar(new Cliente(null,nombre,documento,telefono,email));
    }
}
