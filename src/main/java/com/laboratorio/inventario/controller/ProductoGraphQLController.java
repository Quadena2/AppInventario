package com.laboratorio.inventario.controller;


import com.laboratorio.inventario.dto.CategoriaDTO;
import com.laboratorio.inventario.dto.ProductoDTO;
import com.laboratorio.inventario.model.Categoria;
import com.laboratorio.inventario.model.Producto;
import com.laboratorio.inventario.service.CategoriaService;
import com.laboratorio.inventario.service.ProductoService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoGraphQLController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoGraphQLController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @QueryMapping
    public List<Producto> productos(){
        return productoService.listarTodos();
    }

    @QueryMapping
    public Optional<Producto> producto(@Argument Long id){
        return productoService.obtenerPorId(id);
    }

    @QueryMapping
    public List<Categoria> categorias(){
        return categoriaService.listarTodas();
    }

    @QueryMapping
    public Optional<Categoria> categoria(@Argument Long id){
        return categoriaService.obtenerPorId(id);
    }

    // Mutaciones PRODUCTO
    @MutationMapping
    public Optional<Producto> crearProducto(@Argument ProductoInput input){
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(input.nombre());
        dto.setCategoriaId(input.categoriaId());
        dto.setStock(input.stock());
        dto.setPrecio(input.precio());
        return productoService.agregar(dto);
    }

    @MutationMapping
    public Optional<Producto> actualizarStockProducto(@Argument Long id, @Argument int nuevoStock){
        boolean ok = productoService.actualizarStock(id, nuevoStock);
        if (ok){
            return productoService.obtenerPorId(id);
        }
        return Optional.empty();
    }

    @MutationMapping
    public boolean eliminarProducto(@Argument Long id){
        return productoService.eliminar(id);
    }

    // Mutaciones CATEGORIA
    @MutationMapping
    public Categoria crearCategoria(@Argument CategoriaInput input){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(input.nombre());
        dto.setDescripcion(input.descripcion());
        return categoriaService.agregar(dto);
    }

    @MutationMapping
    public Optional<Categoria> actualizarCategoria(@Argument Long id, @Argument CategoriaInput input){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(input.nombre());
        dto.setDescripcion(input.descripcion());
        return categoriaService.actualizar(id, dto);
    }

    @MutationMapping
    public boolean eliminarCategoria(@Argument Long id){
        return categoriaService.eliminar(id);
    }

    // Inputs internos
    record ProductoInput(String nombre, Long categoriaId, int stock, double precio){}
    record CategoriaInput(String nombre, String descripcion){}
}


















