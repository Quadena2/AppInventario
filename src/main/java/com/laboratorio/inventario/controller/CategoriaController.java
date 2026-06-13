package com.laboratorio.inventario.controller;


import com.laboratorio.inventario.dto.CategoriaDTO;
import com.laboratorio.inventario.model.Categoria;
import com.laboratorio.inventario.service.CategoriaService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService service;


    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar(){
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtener(@PathVariable Long id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Categoria> agregar(@RequestBody CategoriaDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.agregar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto){
        return service.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean ok = service.eliminar(id);
        return ok ? ResponseEntity.ok("Categoria eliminada")
                : ResponseEntity.notFound().build();
    }
}
