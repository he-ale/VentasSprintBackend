package com.mito.facturacionAppbackend.dto;


import com.mito.facturacionAppbackend.models.VentaDetalle;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class VentaDTO {
    private Integer idVenta;
    private LocalDateTime fecha;
    private PersonaDTO persona;
    private Double importe;
    private List<VentaDetalleDTO> ventaDetalles;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public List<VentaDetalleDTO> getVentaDetalles() {
        return ventaDetalles;
    }

    public void setVentaDetalles(List<VentaDetalleDTO> ventaDetalles) {
        this.ventaDetalles = ventaDetalles;
    }
}
