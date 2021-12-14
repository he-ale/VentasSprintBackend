package com.mito.facturacionAppbackend.service.serviceImpl;

import com.mito.facturacionAppbackend.models.Persona;
import com.mito.facturacionAppbackend.repositorio.IGenericRepo;
import com.mito.facturacionAppbackend.repositorio.IPersonaRepo;
import com.mito.facturacionAppbackend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends CRUDImpl<Persona, Integer> implements IPersonaService {
    @Autowired
    private IPersonaRepo repo;


    @Override
    protected IGenericRepo<Persona, Integer> getRepositorio() {
        return repo;
    }
}
