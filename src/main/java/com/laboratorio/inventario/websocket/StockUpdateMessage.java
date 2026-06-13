package com.laboratorio.inventario.websocket;

public class StockUpdateMessage {

    private Long productoId;
    private String nombreProducto;
    private int nuevoStock;

    public StockUpdateMessage() {}

    public StockUpdateMessage(Long productoId, String nombreProducto, int nuevoStock) {
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.nuevoStock = nuevoStock;
    }

    public Long getProductoId() { return productoId; }
    public String getNombreProducto() { return nombreProducto; }
    public int getNuevoStock() { return nuevoStock; }

    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public void setNuevoStock(int nuevoStock) { this.nuevoStock = nuevoStock; }
}
