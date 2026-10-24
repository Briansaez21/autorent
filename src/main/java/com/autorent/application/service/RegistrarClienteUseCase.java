package com.autorent.application.service;
import com.autorent.domain.model.Cliente;
public interface RegistrarClienteUseCase { Cliente registrar(String nombre, String documento, String telefono, String email); }
