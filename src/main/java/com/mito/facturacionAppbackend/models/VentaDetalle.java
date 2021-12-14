package com.mito.facturacionAppbackend.models;

import javax.persistence.*;

@Entity
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVentaDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false, foreignKey = @ForeignKey(name = "FK_detalleVenta_venta"))
    private Venta idVenta;

    @OneToOne
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_detalleVenta_producto"))
    private Producto idProducto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    public Integer getIdVentaDetalle() {
        return idVentaDetalle;
    }

    public void setIdVentaDetalle(Integer idVentaDetalle) {
        this.idVentaDetalle = idVentaDetalle;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
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
