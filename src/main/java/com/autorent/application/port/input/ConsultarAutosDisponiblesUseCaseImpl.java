package com.autorent.application.port.input;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.service.ConsultarAutosDisponiblesUseCase;
import com.autorent.domain.model.Auto;
import java.util.List;
public class ConsultarAutosDisponiblesUseCaseImpl implements ConsultarAutosDisponiblesUseCase {
    private final AutoRepositoryPort repo;
    public ConsultarAutosDisponiblesUseCaseImpl(AutoRepositoryPort repo){this.repo=repo;}
    public List<Auto> consultarDisponibles(){ return repo.buscarDisponibles();}
}
