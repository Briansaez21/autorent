package com.autorent.application.port.input;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.application.service.CrearAlquilerUseCase;
import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.exception.AutoNoDisponibleException;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.model.Alquiler;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAlquiler;
import java.math.BigDecimal;
import java.time.LocalDate;
public class CrearAlquilerUseCaseImpl implements CrearAlquilerUseCase {
    private final AlquilerRepositoryPort alquilerRepo;
    private final AutoRepositoryPort autoRepo;
    private final ClienteRepositoryPort clienteRepo;
    public CrearAlquilerUseCaseImpl(AlquilerRepositoryPort a, AutoRepositoryPort au, ClienteRepositoryPort c){this.alquilerRepo=a;this.autoRepo=au;this.clienteRepo=c;}
    public Alquiler crear(Long clienteId, Long autoId, Integer cantidadDias){
        if(cantidadDias==null||cantidadDias<=0) throw new IllegalArgumentException("La cantidad de dias debe ser mayor que cero");
        clienteRepo.buscarPorId(clienteId).orElseThrow(()->new ClienteNoEncontradoException("Cliente no encontrado con id: "+clienteId));
        Auto auto = autoRepo.buscarPorId(autoId).orElseThrow(()->new AutoNoEncontradoException("Auto no encontrado con id: "+autoId));
        if(!auto.estaDisponible()) throw new AutoNoDisponibleException("El auto no esta disponible para alquilar");
        auto.alquilar(); autoRepo.guardar(auto);
        BigDecimal valorTotal = auto.getPrecioPorDia().multiply(BigDecimal.valueOf(cantidadDias));
        Alquiler alquiler = new Alquiler(null,clienteId,autoId,LocalDate.now(),LocalDate.now().plusDays(cantidadDias),cantidadDias,valorTotal,EstadoAlquiler.ACTIVO);
        return alquilerRepo.guardar(alquiler);
    }
}
