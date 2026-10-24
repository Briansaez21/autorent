package com.autorent.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "alquileres")
public class AlquilerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private Long autoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer cantidadDias;
    private BigDecimal valorTotal;
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getAutoId() { return autoId; }
    public void setAutoId(Long autoId) { this.autoId = autoId; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public Integer getCantidadDias() { return cantidadDias; }
    public void setCantidadDias(Integer cantidadDias) { this.cantidadDias = cantidadDias; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}