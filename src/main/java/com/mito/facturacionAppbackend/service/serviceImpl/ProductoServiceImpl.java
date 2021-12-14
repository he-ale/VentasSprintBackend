package com.mito.facturacionAppbackend.service.serviceImpl;

import com.mito.facturacionAppbackend.models.Producto;
import com.mito.facturacionAppbackend.repositorio.IGenericRepo;
import com.mito.facturacionAppbackend.repositorio.IProductoRepo;
import com.mito.facturacionAppbackend.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends CRUDImpl<Producto, Integer> implements IProductoService {
    @Autowired
    private IProductoRepo repo;

    @Override
    protected IGenericRepo<Producto, Integer> getRepositorio() {
        return repo;
    }
}
