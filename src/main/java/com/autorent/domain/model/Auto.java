package com.autorent.domain.model;

import java.math.BigDecimal;

public class Auto {
    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private BigDecimal precioPorDia;
    private EstadoAuto estado;

    public Auto() {
    }

    public Auto(Long id, String placa, String marca, String modelo, Integer anio, BigDecimal precioPorDia, EstadoAuto estado) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precioPorDia = precioPorDia;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(BigDecimal precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public EstadoAuto getEstado() {
        return estado;
    }

    public void setEstado(EstadoAuto estado) {
        this.estado = estado;
    }

    public boolean estaDisponible() {
        return this.estado == EstadoAuto.DISPONIBLE;
    }

    public void alquilar() {
        if (!estaDisponible()) {
            throw new IllegalStateException("El auto no está disponible para alquilar");
        }
        this.estado = EstadoAuto.ALQUILADO;
    }

    public void ponerDisponible() {
        this.estado = EstadoAuto.DISPONIBLE;
    }

    public void ponerEnMantenimiento() {
        this.estado = EstadoAuto.MANTENIMIENTO;
    }
}
