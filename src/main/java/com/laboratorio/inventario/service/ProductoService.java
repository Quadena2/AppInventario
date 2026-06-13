package com.laboratorio.inventario.service;


import com.laboratorio.inventario.dto.ProductoDTO;
import com.laboratorio.inventario.model.Producto;
import com.laboratorio.inventario.repository.CategoriaRepository;
import com.laboratorio.inventario.repository.ProductoRepository;
import com.laboratorio.inventario.websocket.StockUpdateMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ProductoService(ProductoRepository repository, CategoriaRepository categoriaRepository, SimpMessagingTemplate messagingTemplate) {
        this.productoRepository = repository;
        this.categoriaRepository = categoriaRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> agregar(ProductoDTO dto) {
        return categoriaRepository.findById(dto.getCategoriaId()).map(categoria -> {
            Producto producto = new Producto();
            producto.setNombre(dto.getNombre());
            producto.setCategoria(categoria);
            producto.setStock(dto.getStock());
            producto.setPrecio(dto.getPrecio());
            return productoRepository.save(producto);
        });
    }

    public boolean actualizarStock(Long id, int nuevoStock){
        return productoRepository.findById(id).map(producto -> {
            // Guardar en la db
            producto.setStock(nuevoStock);
            productoRepository.save(producto);

            // evento por WebSocket
            // cualquier cliente android conectado a /topic/stock
            // recibe este mensaje automatico sin hacer ningun get
            messagingTemplate.convertAndSend(
                    "/topic/stock",
                    new StockUpdateMessage(
                            producto.getId(),
                            producto.getNombre(),
                            nuevoStock
                    )
            );


            return true;
        }).orElse(false);
    }

    public boolean eliminar(Long id){
        if (productoRepository.existsById(id)){
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
