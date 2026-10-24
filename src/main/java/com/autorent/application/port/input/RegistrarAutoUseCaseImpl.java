package com.autorent.application.port.input;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.service.RegistrarAutoUseCase;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAuto;
import java.math.BigDecimal;
public class RegistrarAutoUseCaseImpl implements RegistrarAutoUseCase {
    private final AutoRepositoryPort repo;
    public RegistrarAutoUseCaseImpl(AutoRepositoryPort repo){this.repo=repo;}
    public Auto registrar(String placa,String marca,String modelo,Integer anio,BigDecimal precioPorDia){ Auto a=new Auto(null,placa,marca,modelo,anio,precioPorDia,EstadoAuto.DISPONIBLE); return repo.guardar(a);}
}
