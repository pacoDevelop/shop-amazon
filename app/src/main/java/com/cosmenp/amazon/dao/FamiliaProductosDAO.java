package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.FamiliaProducto;

import java.util.List;

@Dao
public interface FamiliaProductosDAO {
    @Query("Select * from familias_productos")
    List<FamiliaProducto> getAll();
    @Query("Select * from familias_productos where id in (:ids)")
    List<FamiliaProducto> getByIds(int[] ids);
    @Query("Select * from familias_productos where id = :id")
    FamiliaProducto get(int id);
    @Insert
    void add(FamiliaProducto FamiliaProducto);
    @Insert
    void addAll(FamiliaProducto... familia_productos);
    @Delete
    void delete(FamiliaProducto FamiliaProducto);
    @Delete
    void deleteAll(FamiliaProducto... familia_productos);
    @Update
    void update(FamiliaProducto FamiliaProducto);

}
