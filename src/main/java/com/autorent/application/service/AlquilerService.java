package com.autorent.application.service;
import com.autorent.application.port.input.*;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.exception.AutoNoDisponibleException;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.model.Alquiler;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAlquiler;
import java.math.BigDecimal;
import java.time.LocalDate;
public class AlquilerService implements CrearAlquilerUseCase, ConsultarAlquilerUseCase, FinalizarAlquilerUseCase {
    private final AlquilerRepositoryPort alquilerRepositoryPort;
    private final AutoRepositoryPort autoRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;
    public AlquilerService(AlquilerRepositoryPort a,AutoRepositoryPort au,ClienteRepositoryPort c){this.alquilerRepositoryPort=a;this.autoRepositoryPort=au;this.clienteRepositoryPort=c;}
    public Alquiler crear(Long clienteId,Long autoId,Integer cantidadDias){
        if(cantidadDias==null||cantidadDias<=0) throw new IllegalArgumentException("La cantidad de dias debe ser mayor que cero");
        clienteRepositoryPort.buscarPorId(clienteId).orElseThrow(()->new ClienteNoEncontradoException("Cliente no encontrado con id: "+clienteId));
        Auto auto=autoRepositoryPort.buscarPorId(autoId).orElseThrow(()->new AutoNoEncontradoException("Auto no encontrado con id: "+autoId));
        if(!auto.estaDisponible()) throw new AutoNoDisponibleException("El auto no esta disponible para alquilar");
        auto.alquilar(); autoRepositoryPort.guardar(auto);
        BigDecimal valorTotal=auto.getPrecioPorDia().multiply(BigDecimal.valueOf(cantidadDias));
        Alquiler alquiler=new Alquiler(null,clienteId,autoId,LocalDate.now(),LocalDate.now().plusDays(cantidadDias),cantidadDias,valorTotal,EstadoAlquiler.ACTIVO);
        return alquilerRepositoryPort.guardar(alquiler);
    }
    public Alquiler consultar(Long id){return alquilerRepositoryPort.buscarPorId(id).orElseThrow(()->new AlquilerNoEncontradoException("Alquiler no encontrado con id: "+id));}
    public Alquiler finalizar(Long id){
        Alquiler alquiler=alquilerRepositoryPort.buscarPorId(id).orElseThrow(()->new AlquilerNoEncontradoException("Alquiler no encontrado con id: "+id));
        alquiler.finalizar(); alquilerRepositoryPort.guardar(alquiler);
        autoRepositoryPort.buscarPorId(alquiler.getAutoId()).ifPresent(auto->{auto.ponerDisponible();autoRepositoryPort.guardar(auto);});
        return alquiler;
    }
}
