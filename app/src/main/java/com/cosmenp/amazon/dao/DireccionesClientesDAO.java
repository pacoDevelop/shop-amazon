package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.DireccionCliente;

import java.util.List;

@Dao
public interface DireccionesClientesDAO {
    @Query("SELECT * from direcciones_clientes")
    List<DireccionCliente> getAll();

    @Query("SELECT * FROM direcciones_clientes WHERE id in(:ids)")
    List<DireccionCliente> getByIds(int[] ids);

    @Query("SELECT * from direcciones_clientes WHERE id= :id")
    DireccionCliente get(int id);

    @Insert
    void add(DireccionCliente direccionCliente);

    @Insert
    void addAll(DireccionCliente... direccionCliente);

    @Delete
    void delete(DireccionCliente direccionCliente);

    @Update
    void update(DireccionCliente direccionCliente);
}
