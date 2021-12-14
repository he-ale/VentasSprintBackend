package com.mito.facturacionAppbackend.controller;

import com.mito.facturacionAppbackend.dto.PersonaDTO;
import com.mito.facturacionAppbackend.exception.ModelNotFountException;
import com.mito.facturacionAppbackend.models.Persona;
import com.mito.facturacionAppbackend.service.IPersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private IPersonaService service;

    @Autowired
    private ModelMapper mapper;

    /**
     * Get
     * */
    @GetMapping
    public ResponseEntity<List<PersonaDTO>> listar() throws Exception {
        List<PersonaDTO>list=service.toList().stream().map(p->mapper.map(p, PersonaDTO.class)).
                collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> litarID(@PathVariable("id") Integer id) throws Exception{
        PersonaDTO obj;
        Persona obj2= service.toListForId(id);
        if (obj2==null) {
            throw new ModelNotFountException("ID no encontrado");
        } else {
            obj= mapper.map(obj2, PersonaDTO.class);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Post
     * */
    @PostMapping
    public ResponseEntity<PersonaDTO> registrar (@Valid @RequestBody PersonaDTO request) throws Exception {
        Persona obj= mapper.map(request, Persona.class);
        Persona obj2= service.toRegister(obj);
        PersonaDTO response= mapper.map(obj2, PersonaDTO.class);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getIdPersona()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Put
     * */
    @PutMapping
    public ResponseEntity<PersonaDTO> modificar (@Valid @RequestBody PersonaDTO request) throws Exception {
        Persona obj= service.toListForId(request.getIdPersona());
        if( obj ==null){
            throw new ModelNotFountException("Id no encontrado "+ obj.getIdPersona());
        }
        Persona obj2= mapper.map(request, Persona.class);
        Persona obj3= service.toModify(obj2);
        PersonaDTO response= mapper.map(obj3, PersonaDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar( @PathVariable("id") Integer id ) throws Exception {
        service.toDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Hateoas
     * */
    @GetMapping("/personaHateoas/{id}")
    public EntityModel<PersonaDTO>listaHateoas( @PathVariable("id") Integer id ) throws Exception {
        Persona obj= service.toListForId(id);
        if ( obj == null ){
            throw new ModelNotFountException("Id no encontrado: " +id);
        }
        PersonaDTO obj3= mapper.map(obj, PersonaDTO.class);
        EntityModel<PersonaDTO> response= EntityModel.of(obj3);
        WebMvcLinkBuilder link1= linkTo(methodOn(this.getClass()).litarID(id));
        response.add(link1.withRel("Persona link"));
        return response;
    }
}
