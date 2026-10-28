package com.autorent.application.service;
import com.autorent.application.port.input.*;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAuto;
import java.math.BigDecimal;
import java.util.List;
public class AutoService implements RegistrarAutoUseCase, ConsultarAutoUseCase, ConsultarAutosDisponiblesUseCase {
    private final AutoRepositoryPort autoRepositoryPort;
    public AutoService(AutoRepositoryPort autoRepositoryPort){this.autoRepositoryPort=autoRepositoryPort;}
    public Auto registrar(String placa,String marca,String modelo,Integer anio,BigDecimal precioPorDia){Auto auto=new Auto(null,placa,marca,modelo,anio,precioPorDia,EstadoAuto.DISPONIBLE);return autoRepositoryPort.guardar(auto);}
    public Auto consultar(Long id){return autoRepositoryPort.buscarPorId(id).orElseThrow(()->new AutoNoEncontradoException("Auto no encontrado con id: "+id));}
    public List<Auto> consultarDisponibles(){return autoRepositoryPort.buscarDisponibles();}
}
