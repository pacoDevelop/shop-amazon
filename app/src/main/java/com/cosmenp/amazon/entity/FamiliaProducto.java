package com.cosmenp.amazon.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "familias_productos")
public class FamiliaProducto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="id_familia")
    @NonNull
    private String idFamilia;
    @NonNull
    private String nombre;
    @NonNull
    private String descripcion;
    @NonNull
    private int activo;

@Ignore
    public FamiliaProducto() {
    }

    public FamiliaProducto(int id, String idFamilia, String nombre, String descripcion, int activo) {
        this.id = id;
        this.idFamilia = idFamilia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(String idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
