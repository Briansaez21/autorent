package com.autorent.application.port.input;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.service.FinalizarAlquilerUseCase;
import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.model.Alquiler;
import com.autorent.domain.model.Auto;
public class FinalizarAlquilerUseCaseImpl implements FinalizarAlquilerUseCase {
    private final AlquilerRepositoryPort alquilerRepo;
    private final AutoRepositoryPort autoRepo;
    public FinalizarAlquilerUseCaseImpl(AlquilerRepositoryPort a, AutoRepositoryPort au){this.alquilerRepo=a;this.autoRepo=au;}
    public Alquiler finalizar(Long id){
        Alquiler alquiler = alquilerRepo.buscarPorId(id).orElseThrow(()->new AlquilerNoEncontradoException("Alquiler no encontrado con id: "+id));
        alquiler.finalizar(); alquilerRepo.guardar(alquiler);
        autoRepo.buscarPorId(alquiler.getAutoId()).ifPresent(auto->{auto.ponerDisponible();autoRepo.guardar(auto);});
        return alquiler;
    }
}
