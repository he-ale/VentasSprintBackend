package com.mito.facturacionAppbackend.controller;

import com.mito.facturacionAppbackend.dto.PersonaDTO;
import com.mito.facturacionAppbackend.dto.ProductoDTO;
import com.mito.facturacionAppbackend.exception.ModelNotFountException;
import com.mito.facturacionAppbackend.models.Persona;
import com.mito.facturacionAppbackend.models.Producto;
import com.mito.facturacionAppbackend.service.IProductoService;
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
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService service;

    @Autowired
    private ModelMapper mapper;

    /**
     * Get
     * */
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() throws Exception {
        List<ProductoDTO>list=service.toList().stream().map(p->mapper.map(p, ProductoDTO.class)).
                collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> litarID(@PathVariable("id") Integer id) throws Exception{
        PersonaDTO obj;
        Producto obj2= service.toListForId(id);
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
    public ResponseEntity<ProductoDTO> registrar (@Valid @RequestBody ProductoDTO request) throws Exception {
        Producto obj= mapper.map(request, Producto.class);
        Producto obj2= service.toRegister(obj);
        ProductoDTO response= mapper.map(obj2, ProductoDTO.class);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getIdProducto()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Put
     * */
    @PutMapping
    public ResponseEntity<ProductoDTO> modificar (@Valid @RequestBody ProductoDTO request) throws Exception {
        Producto obj= service.toListForId(request.getIdProducto());
        if( obj ==null){
            throw new ModelNotFountException("Id no encontrado "+ obj.getIdProducto());
        }
        Producto obj2= mapper.map(request, Producto.class);
        Producto obj3= service.toModify(obj2);
        ProductoDTO response= mapper.map(obj3, ProductoDTO.class);
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
    public EntityModel<ProductoDTO> listaHateoas(@PathVariable("id") Integer id ) throws Exception {
        Producto obj= service.toListForId(id);
        if ( obj == null ){
            throw new ModelNotFountException("Id no encontrado: " +id);
        }
        ProductoDTO obj3= mapper.map(obj, ProductoDTO.class);
        EntityModel<ProductoDTO> response= EntityModel.of(obj3);
        WebMvcLinkBuilder link1= linkTo(methodOn(this.getClass()).litarID(id));
        response.add(link1.withRel("Persona link"));
        return response;
    }
}
