package com.autorent.application.service;
import com.autorent.domain.model.Alquiler;
public interface CrearAlquilerUseCase { Alquiler crear(Long clienteId, Long autoId, Integer cantidadDias); }
