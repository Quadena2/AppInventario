package com.laboratorio.inventario.service;


import com.laboratorio.inventario.dto.CategoriaDTO;
import com.laboratorio.inventario.model.Categoria;
import com.laboratorio.inventario.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;


    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> listarTodas(){
        return repository.findAll();
    }

    public Optional<Categoria> obtenerPorId(Long id){
        return repository.findById(id);
    }
    public Categoria agregar(CategoriaDTO dto){
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return repository.save(categoria);
    }

    public Optional<Categoria> actualizar(Long id, CategoriaDTO dto){
        return repository.findById(id).map(categoria -> {
            categoria.setNombre(dto.getNombre());
            categoria.setDescripcion(dto.getDescripcion());
            return repository.save(categoria);
        });
    }

    public boolean eliminar(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
