package com.peaceathome.model;

import java.math.BigDecimal;

public class Propiedad {
    private int idPropiedad;
    private int idAgente;
    private String direccion;
    private BigDecimal precio;
    private String estado;
    private String descripcion;
    private String tipo;

    public Propiedad() {}

    public Propiedad(int idPropiedad, int idAgente, String direccion, BigDecimal precio, String estado, String descripcion, String tipo) {
        this.idPropiedad = idPropiedad;
        this.idAgente = idAgente;
        this.direccion = direccion;
        this.precio = precio;
        this.estado = estado;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getIdPropiedad() { return idPropiedad; }
    public void setIdPropiedad(int idPropiedad) { this.idPropiedad = idPropiedad; }

    public int getIdAgente() { return idAgente; }
    public void setIdAgente(int idAgente) { this.idAgente = idAgente; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}