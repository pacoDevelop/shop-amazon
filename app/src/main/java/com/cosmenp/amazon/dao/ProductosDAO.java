package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.Producto;

import java.util.List;

@Dao
public interface ProductosDAO {
    @Query("SELECT * from productos")
    List<Producto> getAll();

    @Query("SELECT * from productos WHERE activo=1")
    List<Producto> getAllActivos();

    @Query("SELECT * FROM productos WHERE id in(:ids)")
    List<Producto> getByIds(int[] ids);

    @Query("SELECT * from productos WHERE id= :id")
    Producto get(int id);
    @Query("SELECT * from productos WHERE id_producto= :idProducto")
    Producto get(String idProducto);

    @Query("SELECT COUNT(id) from productos WHERE id_producto= :idProducto AND precio_coste= :precioCoste")
    int get(String idProducto,double precioCoste);
    @Insert
    void add(Producto Producto);

    @Insert
    void addAll(Producto... Producto);

    @Delete
    void delete(Producto Producto);

    @Update
    void update(Producto Producto);
}
