package com.cosmenp.amazon.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "productos")
public class Producto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="id_producto")
    @NonNull
    private String idProducto;
    @ColumnInfo(name="id_familia")
    @NonNull
    private String idFamilia;
    @ColumnInfo(name="tipo_iva")
    @NonNull
    private double tipoIva;
    @ColumnInfo(name="precio_coste")
    @NonNull
    private double precioCoste;
    @NonNull
    private double pvp;
    @NonNull
    private String descripcion;
    @ColumnInfo(name="codigo_barras")
    @NonNull
    private String codigoBarras;
    @ColumnInfo(name="id_proovedor")
    @NonNull
    private int idProovedor;
    @ColumnInfo(name="stock_actual")
    @NonNull
    private int stockActual;
    @ColumnInfo(name="stock_minimo")
    @NonNull
    private int stockMinimo;
    @ColumnInfo(name="stock_maximo")
    @NonNull
    private int stockMaximo;
    @ColumnInfo(name="ruta_foto")
    @NonNull
    private String rutaFoto;
    @NonNull
    private int activo;
    @Ignore
    public Producto() {
    }
@Ignore
    public Producto(String idProducto,double precioCoste, double pvp, @NonNull String descripcion, @NonNull String rutaFoto) {
    this.idProducto = idProducto;
    this.idFamilia = "";
    this.tipoIva = 0;
    this.precioCoste = precioCoste;
    this.pvp = pvp;
    this.descripcion = descripcion;
    this.codigoBarras = "";
    this.idProovedor = 0;
    this.stockActual = 0;
    this.stockMinimo = 0;
    this.stockMaximo = 0;
    this.rutaFoto = rutaFoto;
    this.activo = 1;
    }

    public Producto(int id, @NonNull String idProducto, @NonNull String idFamilia, double tipoIva, double precioCoste, double pvp, @NonNull String descripcion, @NonNull String codigoBarras, int idProovedor, int stockActual, int stockMinimo, int stockMaximo, @NonNull String rutaFoto, int activo) {
        this.id = id;
        this.idProducto = idProducto;
        this.idFamilia = idFamilia;
        this.tipoIva = tipoIva;
        this.precioCoste = precioCoste;
        this.pvp = pvp;
        this.descripcion = descripcion;
        this.codigoBarras = codigoBarras;
        this.idProovedor = idProovedor;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.rutaFoto = rutaFoto;
        this.activo = activo;
    }

    protected Producto(Parcel in) {
        id = in.readInt();
        idProducto = in.readString();
        idFamilia = in.readString();
        tipoIva = in.readDouble();
        precioCoste = in.readDouble();
        pvp = in.readDouble();
        descripcion = in.readString();
        codigoBarras = in.readString();
        idProovedor = in.readInt();
        stockActual = in.readInt();
        stockMinimo = in.readInt();
        stockMaximo = in.readInt();
        rutaFoto = in.readString();
        activo = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(@NonNull String idProducto) {
        this.idProducto = idProducto;
    }

    @NonNull
    public String getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(@NonNull String idFamilia) {
        this.idFamilia = idFamilia;
    }

    public double getTipoIva() {
        return tipoIva;
    }

    public void setTipoIva(double tipoIva) {
        this.tipoIva = tipoIva;
    }

    public double getPrecioCoste() {
        return precioCoste;
    }

    public void setPrecioCoste(double precioCoste) {
        this.precioCoste = precioCoste;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }

    @NonNull
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(@NonNull String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getIdProovedor() {
        return idProovedor;
    }

    public void setIdProovedor(int idProovedor) {
        this.idProovedor = idProovedor;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    @NonNull
    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(@NonNull String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", idProducto='" + idProducto + '\'' +
                ", idFamilia='" + idFamilia + '\'' +
                ", tipoIva=" + tipoIva +
                ", precioCoste=" + precioCoste +
                ", pvp=" + pvp +
                ", descripcion='" + descripcion + '\'' +
                ", codigoBarras='" + codigoBarras + '\'' +
                ", idProovedor=" + idProovedor +
                ", stockActual=" + stockActual +
                ", stockMinimo=" + stockMinimo +
                ", stockMaximo=" + stockMaximo +
                ", rutaFoto='" + rutaFoto + '\'' +
                ", activo=" + activo +
                '}';
    }
}
