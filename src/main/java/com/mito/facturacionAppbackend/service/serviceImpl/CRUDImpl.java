package com.mito.facturacionAppbackend.service.serviceImpl;

import com.mito.facturacionAppbackend.repositorio.IGenericRepo;
import com.mito.facturacionAppbackend.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T,ID> {
    protected abstract IGenericRepo<T, ID> getRepositorio();

    @Override
    public T toRegister(T d) throws Exception {
        return getRepositorio().save(d);
    }

    @Override
    public T toModify(T d) throws Exception {
        return getRepositorio().save(d);
    }

    @Override
    public List<T> toList() throws Exception {
        return getRepositorio().findAll();
    }

    @Override
    public T toListForId(ID id) throws Exception {
        return getRepositorio().findById(id).orElse(null);
    }

    @Override
    public void toDelete(ID id) throws Exception {
        getRepositorio().deleteById(id);
    }
}
