package com.laboratorio.inventario;


import com.laboratorio.inventario.model.Categoria;
import com.laboratorio.inventario.model.Producto;
import com.laboratorio.inventario.repository.CategoriaRepository;
import com.laboratorio.inventario.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public DataLoader(ProductoRepository repository, CategoriaRepository categoriaRepository) {
        this.productoRepository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) {
        if (productoRepository.count() == 0){
            Categoria tec = categoriaRepository.save(new Categoria("Tecnología", "Equipos y accesorios tecnológicos"));
            Categoria mob = categoriaRepository.save(new Categoria("Mobiliario", "Muebles y accesorios de oficina"));
            Categoria pap = categoriaRepository.save(new Categoria("Papelería", "Artículos de escritorio y oficina"));

            productoRepository.save(new Producto(null, "Laptop Dell", tec, 10, 850.00));
            productoRepository.save(new Producto(null, "Mouse Logitech", tec, 25, 35.00));
            productoRepository.save(new Producto(null, "Teclado Mecánico", tec, 15, 75.00));
            productoRepository.save(new Producto(null, "Silla Ergonómica", mob, 5, 280.00));
            productoRepository.save(new Producto(null, "Resma de Papel", pap, 50, 8.50));

            System.out.println("✅ Categorías y productos cargados");
        }
    }
}
