package com.cosmenp.amazon.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cosmenp.amazon.dao.ClientesDAO;
import com.cosmenp.amazon.dao.DireccionesClientesDAO;
import com.cosmenp.amazon.dao.EmpleadosDAO;
import com.cosmenp.amazon.dao.FamiliaProductosDAO;
import com.cosmenp.amazon.dao.LineasPedidosDAO;
import com.cosmenp.amazon.dao.PedidosDAO;
import com.cosmenp.amazon.dao.ProductosDAO;
import com.cosmenp.amazon.entity.Cliente;
import com.cosmenp.amazon.entity.DireccionCliente;
import com.cosmenp.amazon.entity.Empleado;
import com.cosmenp.amazon.entity.FamiliaProducto;
import com.cosmenp.amazon.entity.LineaPedido;
import com.cosmenp.amazon.entity.Pedido;
import com.cosmenp.amazon.entity.Producto;


@Database(entities = {Cliente.class, DireccionCliente.class, Empleado.class, FamiliaProducto.class, LineaPedido.class, Pedido.class, Producto.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClientesDAO clientesDAO();
    public abstract DireccionesClientesDAO direccionesClientesDAO();
    public abstract EmpleadosDAO empleadosDAO();
    public abstract FamiliaProductosDAO familiaProductosDAO();
    public abstract LineasPedidosDAO lineasPedidosDAO();
    public abstract PedidosDAO pedidosDAO();
    public abstract ProductosDAO productosDAO();
}
