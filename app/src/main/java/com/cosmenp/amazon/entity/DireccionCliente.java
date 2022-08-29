package com.cosmenp.amazon.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity ( tableName = "direcciones_clientes")
public class DireccionCliente {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo ( name = "id_cliente")
    @NonNull
    private String idCliente;
    @NonNull
    private String direccion;
    @ColumnInfo (name = "cod_postal")
    @NonNull
    private String codPostal;
    @NonNull
    private String localidad;
    @NonNull
    private String provincia;
    @NonNull
    private String pais;
    @NonNull
    private boolean activo;
    @Ignore
    public DireccionCliente() {
    }

    public DireccionCliente(int id, @NonNull String idCliente, @NonNull String direccion, @NonNull String codPostal, @NonNull String localidad, @NonNull String provincia, @NonNull String pais, boolean activo) {
        this.id = id;
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.codPostal = codPostal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(@NonNull String idCliente) {
        this.idCliente = idCliente;
    }

    @NonNull
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NonNull String direccion) {
        this.direccion = direccion;
    }

    @NonNull
    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(@NonNull String codPostal) {
        this.codPostal = codPostal;
    }

    @NonNull
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(@NonNull String localidad) {
        this.localidad = localidad;
    }

    @NonNull
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(@NonNull String provincia) {
        this.provincia = provincia;
    }

    @NonNull
    public String getPais() {
        return pais;
    }

    public void setPais(@NonNull String pais) {
        this.pais = pais;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
