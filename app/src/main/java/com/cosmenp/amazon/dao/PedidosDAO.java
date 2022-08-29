package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.Pedido;

import java.util.List;

@Dao
public interface PedidosDAO {
    @Query("SELECT * from pedidos")
    List<Pedido> getAll();

    @Query("SELECT * FROM pedidos WHERE id in(:ids)")
    List<Pedido> getByIds(int[] ids);

    @Query("SELECT COUNT(id) FROM pedidos")
    int getCountAll();

    @Query("SELECT * from pedidos WHERE id= :id")
    Pedido get(int id);

    @Query("SELECT * from pedidos WHERE id_pedido= :idPedido")
    Pedido get(String idPedido);

    @Query("SELECT MAX(id_pedido) FROM pedidos")
    String getCod();

    @Insert
    void add(Pedido Pedido);

    @Insert
    void addAll(Pedido... Pedido);

    @Delete
    void delete(Pedido Pedido);

    @Query("DELETE FROM pedidos")
    void deleteAll();

    @Update
    void update(Pedido Pedido);
}
