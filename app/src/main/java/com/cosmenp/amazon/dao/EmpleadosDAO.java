package com.cosmenp.amazon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cosmenp.amazon.entity.Empleado;

import java.util.List;

@Dao
public interface EmpleadosDAO {
    @Query("Select * from empleados")
    List<Empleado> getAll();
    @Query("Select * from empleados where id in (:ids)")
    List<Empleado> getByIds(int[] ids);
    @Query("Select * from empleados where id = :id")
    Empleado get(int id);
    @Insert
    void add(Empleado Empleado);
    @Insert
    void addAll(Empleado... empleados);
    @Delete
    void delete(Empleado Empleado);
    @Delete
    void deleteAll(Empleado... empleados);
    @Update
    void update(Empleado Empleado);

}
