package com.mito.facturacionAppbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mito.facturacionAppbackend.models.Producto;

import javax.validation.constraints.NotNull;

public class VentaDetalleDTO {
    private Integer idVentaDetalle;
    @JsonIgnore
    private VentaDTO idVenta;
    private Producto idProducto;
    private Integer cantidad;

    public Integer getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(Integer idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public VentaDTO getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(VentaDTO idVenta) {
        this.idVenta = idVenta;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
