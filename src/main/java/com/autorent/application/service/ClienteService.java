package com.autorent.application.service;
import com.autorent.application.port.input.*;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.exception.ClienteYaExisteException;
import com.autorent.domain.model.Cliente;
public class ClienteService implements RegistrarClienteUseCase, ConsultarClienteUseCase {
    private final ClienteRepositoryPort clienteRepositoryPort;
    public ClienteService(ClienteRepositoryPort p){this.clienteRepositoryPort=p;}
    public Cliente registrar(String nombre,String documento,String telefono,String email){
        if(clienteRepositoryPort.buscarPorDocumento(documento).isPresent()) throw new ClienteYaExisteException("Ya existe un cliente con documento: "+documento);
        return clienteRepositoryPort.guardar(new Cliente(null,nombre,documento,telefono,email));
    }
    public Cliente consultar(Long id){return clienteRepositoryPort.buscarPorId(id).orElseThrow(()->new ClienteNoEncontradoException("Cliente no encontrado con id: "+id));}
}
