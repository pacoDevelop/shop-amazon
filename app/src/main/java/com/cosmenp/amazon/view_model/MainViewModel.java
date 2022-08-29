package com.cosmenp.amazon.view_model;

import androidx.lifecycle.ViewModel;

import com.cosmenp.amazon.entity.LineaPedido;
import com.cosmenp.amazon.entity.Pedido;
import com.cosmenp.amazon.entity.Producto;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private List<Pedido> pedidos;
    private List<LineaPedido> lineasPedidoCarrito;
    private List<LineaPedido> lineasPedidosRealizado;
    private List<Producto> productos;
    private List<Producto> productosActivo;
    private Pedido selectedPed;
    private Pedido pedidoActual;
    private int selectedProd;

    public void initialize() {
        if (pedidos == null) {
            this.pedidos = new ArrayList<>();
            this.productos = new ArrayList<>();
            this.productosActivo = new ArrayList<>();
            this.lineasPedidoCarrito = new ArrayList<>();
            this.lineasPedidosRealizado = new ArrayList<>();
            this.pedidoActual = null;
            selectedPed = new Pedido();
            selectedProd = -1;
        }
    }

    public void addPedido(Pedido pedido) {
        initialize();
        pedidos.add(pedido);
    }

    public void addProducto(Producto producto) {
        initialize();
        productos.add(producto);
    }

    public List<LineaPedido> getLineasPedidoCarrito() {
        initialize();

        return lineasPedidoCarrito;
    }

    public void setLineasPedidoCarrito(List<LineaPedido> lineasPedidoCarrito) {
        initialize();
        this.lineasPedidoCarrito = lineasPedidoCarrito;
    }

    public List<Pedido> getPedidos() {
        initialize();
        return pedidos;
    }

    public Pedido getPedido(int position) {
        initialize();
        if (position >= 0 && position < pedidos.size()) {
            return pedidos.get(position);
        } else {
            return null;
        }
    }

    public void setPedidos(List<Pedido> pedidos) {

        initialize();
        this.pedidos = pedidos;
    }

    public Pedido getSelectedPed() {

        initialize();
        return selectedPed;
    }

    public void setSelectedPed(Pedido selectedPed) {
        initialize();
        this.selectedPed = selectedPed;
    }

    public int getSelectedProd() {

        initialize();
        return selectedProd;
    }

    public void setSelectedProd(int selectedProd) {
        initialize();
        this.selectedProd = selectedProd;
    }

    public List<Producto> getProductos() {
        initialize();
        return productos;
    }

    public Producto getProducto(int position) {
        if (position >= 0 && position < productos.size()) {
            return productos.get(position);
        } else {
            return null;
        }
    }

    public Producto getProductoActivo(int position) {
        if (position >= 0 && position < productosActivo.size()) {
            return productosActivo.get(position);
        } else {
            return null;
        }
    }

    public void setProductos(List<Producto> productos) {
        initialize();
        this.productos = productos;
    }

    public List<LineaPedido> getLineasPedidosRealizado() {
        initialize();
        return lineasPedidosRealizado;
    }

    public void addLineaPedidoCarrito(LineaPedido lineaPedido) {
        initialize();
        boolean nuevo = true;
        int i = 0;
        while (nuevo && i < lineasPedidoCarrito.size()) {
            if (lineasPedidoCarrito.get(i).getIdProducto().equals(lineaPedido.getIdProducto())) {
                lineasPedidoCarrito.get(i).setUnidades(lineasPedidoCarrito.get(i).getUnidades() + 1);
                nuevo = false;
            }
            i++;
        }

        if (nuevo)
            lineasPedidoCarrito.add(lineaPedido);
    }

    public void addLineaPedidoCarrito(int position) {
        initialize();
        lineasPedidoCarrito.get(position).setUnidades(lineasPedidoCarrito.get(position).getUnidades() + 1);
    }

    public void deleteLineaPedidoCarrito(LineaPedido lineaPedido) {
        initialize();
        boolean eliminado = false;
        int i = 0;
        while (!eliminado && i < lineasPedidoCarrito.size()) {
            if (lineasPedidoCarrito.get(i).getIdProducto().equals(lineaPedido.getIdProducto())) {
                int unidades = lineasPedidoCarrito.get(i).getUnidades();
                if (unidades > 1)
                    lineasPedidoCarrito.get(i).setUnidades(lineasPedidoCarrito.get(i).getUnidades() - 1);
                else
                    lineasPedidoCarrito.remove(i);
                eliminado = true;
            }
            i++;
        }
    }

    public void deleteLineaPedidoCarrito(int position) {
        initialize();
        int unidades = lineasPedidoCarrito.get(position).getUnidades();
        if (unidades > 1)
            lineasPedidoCarrito.get(position).setUnidades(lineasPedidoCarrito.get(position).getUnidades() - 1);
        else
            lineasPedidoCarrito.remove(position);
    }

    public void deleteAllLineaPedidoCarrito() {
        initialize();
        lineasPedidoCarrito.clear();
    }


    public void deleteAllLineaPedidoRealizado() {
        initialize();
        lineasPedidosRealizado.clear();
    }

    public void setLineasPedidosRealizado(List<LineaPedido> lineasPedidosRealizado) {
        this.lineasPedidosRealizado = lineasPedidosRealizado;
    }

    public Pedido getPedidoActual() {
        initialize();
        return pedidoActual;
    }

    public void setPedidoActual(Pedido pedidoActual) {

        initialize();
        this.pedidoActual = pedidoActual;
    }

    public void setSelectedProd(String idProducto) {
        initialize();
        int i=0;
        boolean seleccionado = false;
        while (!seleccionado && i < productos.size()){
            if (productos.get(i).getIdProducto().equals(idProducto)){
                seleccionado = true;
                setSelectedProd(i);
            }
            i++;
        }
    }

    public List<Producto> getProductosActivo() {
        initialize();
        return productosActivo;
    }

    public void setProductosActivo(List<Producto> productosActivo) {
        initialize();
        this.productosActivo = productosActivo;
    }

    public Producto getProducto(Producto productoActivo) {
        initialize();
        Producto p = null;
        int i=0;
        boolean encontrado = false;
        while (!encontrado && i < productos.size()){
            if (productos.get(i).getId() == productoActivo.getId()){
                encontrado = true;
                p = productos.get(i);
            }
            i++;
        }

        return p;
    }
}
