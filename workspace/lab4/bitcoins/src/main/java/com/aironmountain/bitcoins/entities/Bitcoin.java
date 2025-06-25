package com.aironmountain.bitcoins.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "bitcoin")
public class Bitcoin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double cantidad;
    private Double valor;
    private LocalDate fecha;

    @Column(name = "idusuario")
    private Long idusuario;
       

    public Bitcoin() {
    }
    public Bitcoin(Double cantidad, Double valor, Long idusuario) {
        this.cantidad = cantidad;
        this.valor = valor;
        this.idusuario = idusuario;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getCantidad() {
        return cantidad;
    }
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Long getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
   

    

}
