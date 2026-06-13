package com.laboratorio.inventario.dto;


import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private Long categoriaId;
    private int stock;
    private double precio;
}
