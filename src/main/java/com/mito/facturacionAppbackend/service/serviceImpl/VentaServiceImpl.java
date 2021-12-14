package com.mito.facturacionAppbackend.service.serviceImpl;

import com.mito.facturacionAppbackend.models.Venta;
import com.mito.facturacionAppbackend.models.VentaDetalle;
import com.mito.facturacionAppbackend.repositorio.IGenericRepo;
import com.mito.facturacionAppbackend.repositorio.IVentaRepo;
import com.mito.facturacionAppbackend.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {
    @Autowired
    private IVentaRepo repo;

    @Override
    protected IGenericRepo<Venta, Integer> getRepositorio() {
        return repo;
    }



    @Transactional
    @Override
    public Venta registrarVenta(Venta v) throws Exception {
        v.getVentaDetalles().forEach(det -> det.setIdVenta(v));
        repo.save(v);
        return v;
    }
}
