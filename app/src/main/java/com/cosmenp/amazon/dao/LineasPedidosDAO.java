package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.LineaPedido;

import java.util.List;

@Dao
public interface LineasPedidosDAO {
    @Query("SELECT * from lineas_pedidos")
    List<LineaPedido> getAll();

    @Query("SELECT * FROM lineas_pedidos WHERE id in(:ids)")
    List<LineaPedido> getByIds(int[] ids);

    @Query("SELECT * from lineas_pedidos WHERE id= :id")
    LineaPedido get(int id);

    @Query("SELECT * from lineas_pedidos WHERE id_pedido= :idPedido")
    List<LineaPedido> get(String idPedido);

    @Insert
    void add(LineaPedido lineaPedido);

    @Insert
    void addAll(LineaPedido... lineaPedido);

    @Delete
    void delete(LineaPedido lineaPedido);

    @Query("DELETE FROM lineas_pedidos")
    void deleteAll();

    @Update
    void update(LineaPedido lineaPedido);
}
