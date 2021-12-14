package com.mito.facturacionAppbackend.controller;

import com.mito.facturacionAppbackend.dto.VentaDTO;
import com.mito.facturacionAppbackend.exception.ModelNotFountException;
import com.mito.facturacionAppbackend.models.Venta;
import com.mito.facturacionAppbackend.service.IVentaService;
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
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private IVentaService service;

    @Autowired
    private ModelMapper mapper;

    /**
     * Get
     * */
    @GetMapping
    public ResponseEntity<List<VentaDTO>> listar() throws Exception{
        List<VentaDTO> list= service.toList().stream().map(v-> mapper.map(v, VentaDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VentaDTO> listarID(@PathVariable("id") Integer id) throws Exception {
        VentaDTO obj;
        Venta obj2=service.toListForId(id);
        if(obj2 == null){
            throw new ModelNotFountException("Id no encontrado "+ id);
        }else {
            obj= mapper.map(obj2, VentaDTO.class);
        }
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Post
     * */
    @PostMapping
    public ResponseEntity<VentaDTO> registrar(@Valid @RequestBody VentaDTO request) throws Exception {

        Venta obj2= mapper.map(request, Venta.class);
        /*List<VentaDetalle> ventaDetalles= mapper.map(request.getVentaDetalles(),
                new TypeToken<List<VentaDetalle>>(){}.getType());*/
        Venta obj3=service.registrarVenta(obj2);
        VentaDTO response= mapper.map(obj3 , VentaDTO.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getIdVenta()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Put
     * */
    @PutMapping
    public ResponseEntity<VentaDTO> modificar(@Valid @RequestBody VentaDTO request) throws Exception{
        Venta v= service.toListForId(request.getIdVenta());

        if(v == null) {
            throw new ModelNotFountException("ID NO ENCONTRADO " + request.getIdVenta());
        }

        Venta obj= mapper.map(request, Venta.class);
        Venta obj2= service.toModify(obj);
        VentaDTO response= mapper.map(obj2, VentaDTO.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Delete
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        service.toDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Hateoas
     * */
    @GetMapping("/ventas/{id}")
    public EntityModel<VentaDTO> listHateoas(@PathVariable("id") Integer id)throws Exception{
        Venta obj= service.toListForId(id);
        if(obj==null){
            throw new ModelNotFountException("No se encontro el id: " +id);
        }
        VentaDTO dto= mapper.map(obj, VentaDTO.class);
        EntityModel<VentaDTO> response= EntityModel.of(dto);
        WebMvcLinkBuilder link1= linkTo(methodOn(this.getClass()).listarID(id));
        response.add(link1.withRel("doctor link"));
        return response;
    }
}
