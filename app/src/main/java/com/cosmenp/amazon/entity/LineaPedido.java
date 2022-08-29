package com.cosmenp.amazon.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "lineas_pedidos")
public class LineaPedido {
    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    @ColumnInfo(name = "id_pedido")
    String idPedido;
    @NonNull
    @ColumnInfo(name = "id_producto")
    String idProducto;
    @NonNull
    Integer unidades;
    @NonNull
    String descripcion;
    @NonNull
    Double pvp;
    @NonNull
    @ColumnInfo(name = "tipo_iva")
    Integer tipoIva;
    @NonNull
    boolean activo;
@Ignore
    public LineaPedido() {
    }

    public LineaPedido(int id, @NonNull String idPedido, @NonNull String idProducto, @NonNull Integer unidades, @NonNull String descripcion, @NonNull Double pvp, @NonNull Integer tipoIva, boolean activo) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.tipoIva = tipoIva;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(@NonNull String idPedido) {
        this.idPedido = idPedido;
    }

    @NonNull
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(@NonNull String idProducto) {
        this.idProducto = idProducto;
    }

    @NonNull
    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(@NonNull Integer unidades) {
        this.unidades = unidades;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public Double getPvp() {
        return pvp;
    }

    public void setPvp(@NonNull Double pvp) {
        this.pvp = pvp;
    }

    @NonNull
    public Integer getTipoIva() {
        return tipoIva;
    }

    public void setTipoIva(@NonNull Integer tipoIva) {
        this.tipoIva = tipoIva;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
