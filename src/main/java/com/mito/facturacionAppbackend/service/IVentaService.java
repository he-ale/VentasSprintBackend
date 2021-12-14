package com.mito.facturacionAppbackend.service;

import com.mito.facturacionAppbackend.models.Venta;
import com.mito.facturacionAppbackend.models.VentaDetalle;

import java.util.List;

public interface IVentaService extends ICRUD<Venta, Integer> {
    Venta registrarVenta ( Venta v ) throws Exception;
}
