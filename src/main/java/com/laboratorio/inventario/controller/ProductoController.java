package com.laboratorio.inventario.controller;

import com.laboratorio.inventario.dto.ProductoDTO;
import com.laboratorio.inventario.model.Producto;
import com.laboratorio.inventario.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id){
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Producto> agregar(@RequestBody ProductoDTO dto){
        return service.agregar(dto)
                .map(producto -> ResponseEntity.status(HttpStatus.CREATED).body(producto))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<String> actualizarStock(
            @PathVariable Long id,
            @RequestParam int nuevoStock
    ){
        boolean ok = service.actualizarStock(id, nuevoStock);
        return ok ? ResponseEntity.ok("Stock Actualizado")
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        boolean ok = service.eliminar(id);
        return ok ? ResponseEntity.ok("Producto eliminado")
                : ResponseEntity.notFound().build();
    }
}
