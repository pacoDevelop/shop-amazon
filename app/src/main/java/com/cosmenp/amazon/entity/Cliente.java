package com.cosmenp.amazon.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "clientes")
public class Cliente {
    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo (name = "id_cliente")
    @NonNull
    private String idCliente;
    @ColumnInfo (name = "id_Usuario")
    @NonNull
    private String idUsuario;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido1;
    @NonNull
    private String apellido2;
    @NonNull
    private String nif;
    @NonNull
    private String sexo;
    @NonNull
    private String numcta;
    @NonNull
    @ColumnInfo (name = "como_nos_conocio")
    private String comoNosConocio;
    private int activo;
    @Ignore
    public Cliente() {
    }

    public Cliente(int id, @NonNull String idCliente, @NonNull String idUsuario, @NonNull String nombre, @NonNull String apellido1, @NonNull String apellido2, @NonNull String nif, @NonNull String sexo, @NonNull String numcta, @NonNull String comoNosConocio, int activo) {
        this.id = id;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nif = nif;
        this.sexo = sexo;
        this.numcta = numcta;
        this.comoNosConocio = comoNosConocio;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNumcta() {
        return numcta;
    }

    public void setNumcta(String numcta) {
        this.numcta = numcta;
    }

    public String getComoNosConocio() {
        return comoNosConocio;
    }

    public void setComoNosConocio(String comoNosConocio) {
        this.comoNosConocio = comoNosConocio;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
