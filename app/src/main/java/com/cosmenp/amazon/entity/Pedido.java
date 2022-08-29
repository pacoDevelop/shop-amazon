package com.cosmenp.amazon.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "pedidos")
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    @ColumnInfo(name = "id_pedido")
    String idPedido;
    @NonNull
    @ColumnInfo(name = "id_empleado_empaqueta")
    String idEmpleadoEmpaqueta;
    @NonNull
    @ColumnInfo(name = "id_empleado_transporte")
    String idEmpleadoTransporte;
    @NonNull
    @ColumnInfo(name = "fecha_pedido")
    @TypeConverters(DateConverter.class)
    Date fechaPedido;
    @NonNull
    @ColumnInfo(name = "fecha_envio")
    @TypeConverters(DateConverter.class)
    Date fechaEnvio;
    @NonNull
    @ColumnInfo(name = "fecha_entrega")
    @TypeConverters(DateConverter.class)
    Date fechaEntrega;
    @NonNull
    Integer facturado;
    @NonNull
    String idFactura;
    @NonNull
    @ColumnInfo(name = "fecha_factura")
    @TypeConverters(DateConverter.class)
    Date fechaFactura;
    @NonNull
    Integer pagado;
    @NonNull
    @ColumnInfo(name = "fecha_pago")
    @TypeConverters(DateConverter.class)
    Date fechaPago;
    @NonNull
    String metodoPago;
    @NonNull
    @ColumnInfo(name = "id_cliente")
    Integer idCliente;
    @NonNull
    boolean activo;
    @Ignore
    public Pedido() {
    }

    public Pedido(int id, @NonNull String idPedido, @NonNull String idEmpleadoEmpaqueta, @NonNull String idEmpleadoTransporte, @NonNull Date fechaPedido, @NonNull Date fechaEnvio, @NonNull Date fechaEntrega, @NonNull Integer facturado, @NonNull String idFactura, @NonNull Date fechaFactura, @NonNull Integer pagado, @NonNull Date fechaPago, @NonNull String metodoPago, @NonNull Integer idCliente, boolean activo) {
        this.id = id;
        this.idPedido = idPedido;
        this.idEmpleadoEmpaqueta = idEmpleadoEmpaqueta;
        this.idEmpleadoTransporte = idEmpleadoTransporte;
        this.fechaPedido = fechaPedido;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
        this.facturado = facturado;
        this.idFactura = idFactura;
        this.fechaFactura = fechaFactura;
        this.pagado = pagado;
        this.fechaPago = fechaPago;
        this.metodoPago = metodoPago;
        this.idCliente = idCliente;
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
    public String getIdEmpleadoEmpaqueta() {
        return idEmpleadoEmpaqueta;
    }

    public void setIdEmpleadoEmpaqueta(@NonNull String idEmpleadoEmpaqueta) {
        this.idEmpleadoEmpaqueta = idEmpleadoEmpaqueta;
    }

    @NonNull
    public String getIdEmpleadoTransporte() {
        return idEmpleadoTransporte;
    }

    public void setIdEmpleadoTransporte(@NonNull String idEmpleadoTransporte) {
        this.idEmpleadoTransporte = idEmpleadoTransporte;
    }

    @NonNull
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(@NonNull Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @NonNull
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(@NonNull Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @NonNull
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(@NonNull Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @NonNull
    public Integer getFacturado() {
        return facturado;
    }

    public void setFacturado(@NonNull Integer facturado) {
        this.facturado = facturado;
    }

    @NonNull
    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(@NonNull String idFactura) {
        this.idFactura = idFactura;
    }

    @NonNull
    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(@NonNull Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    @NonNull
    public Integer getPagado() {
        return pagado;
    }

    public void setPagado(@NonNull Integer pagado) {
        this.pagado = pagado;
    }

    @NonNull
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(@NonNull Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @NonNull
    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(@NonNull String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @NonNull
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(@NonNull Integer idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
