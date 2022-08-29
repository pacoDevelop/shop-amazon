package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.Cliente;

import java.util.List;

@Dao
public interface ClientesDAO {
    @Query("SELECT * from clientes")
    List<Cliente> getAll();

    @Query("SELECT * FROM clientes WHERE id in(:ids)")
    List<Cliente> getByIds(int[] ids);

    @Query("SELECT * from clientes WHERE id= :id")
    Cliente get(int id);

    @Insert
    void add (Cliente Cliente);

    @Insert
    void addAll(Cliente... Cliente);

    @Delete
    void delete (Cliente Cliente);

    @Update
    void update (Cliente Cliente);
}
