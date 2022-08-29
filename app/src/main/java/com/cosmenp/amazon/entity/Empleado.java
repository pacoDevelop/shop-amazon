package com.cosmenp.amazon.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "empleados")
public class Empleado {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name="id_empleado")
    @NonNull
    private String idEmpleado;
    @NonNull
    @ColumnInfo(name="id_usuario")
    private String idUsuario;
    @NonNull
    private String nombre;
    @NonNull
    private String apellido1;
    @NonNull
    private String apellido2;
    @NonNull
    @ColumnInfo(name="numcat")
    private String numCat;
    @NonNull
    private String movil;
    @NonNull
    private String lcoalidad;
    @NonNull
    @ColumnInfo(name="cod_postal")
    private String codPostal;
    @NonNull
    private String provincia;
    @NonNull
    private int activo;
@Ignore
    public Empleado() {
    }

    public Empleado(int id, String idEmpleado, String idUsuario, String nombre, String apellido1, String apellido2, String numCat, String movil, String lcoalidad, String codPostal, String provincia, int activo) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numCat = numCat;
        this.movil = movil;
        this.lcoalidad = lcoalidad;
        this.codPostal = codPostal;
        this.provincia = provincia;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public String getNumCat() {
        return numCat;
    }

    public void setNumCat(String numCat) {
        this.numCat = numCat;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getLcoalidad() {
        return lcoalidad;
    }

    public void setLcoalidad(String lcoalidad) {
        this.lcoalidad = lcoalidad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
