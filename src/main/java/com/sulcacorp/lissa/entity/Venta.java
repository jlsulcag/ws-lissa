/*
 * package com.sulcacorp.lissa.model;
 * 
 * import java.math.BigDecimal; import java.time.LocalDateTime; import
 * java.util.List;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Column; import
 * javax.persistence.Entity; import javax.persistence.FetchType; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.ManyToOne; import
 * javax.persistence.OneToMany; import javax.persistence.Table; import
 * javax.validation.constraints.Size;
 * 
 * import com.fasterxml.jackson.databind.annotation.JsonSerialize; import
 * com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
 * 
 * 
 * //@Entity //@Table(name = "venta") public class Venta {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long idventa;
 * 
 * @Column(name = "fecha_emision", nullable = false)
 * 
 * @JsonSerialize(using = ToStringSerializer.class)//permite manejar un formato
 * estandar de fecha internacional private LocalDateTime fechaEmision;
 * 
 * @Column(name = "serie_comprobante", nullable = false, length = 4)
 * 
 * @Size(min = 4, max = 4, message = "Serie : debe tener 4 caracteres") private
 * String serieComprobante;
 * 
 * @Column(name = "nro_comprobante", nullable = false, length = 8)
 * 
 * @Size(min = 8, max = 8, message =
 * "Numero de comprobante : debe tener 8 cracteres") private long
 * nroComprobante;
 * 
 * @Column(name = "estado", nullable = false, length = 10)
 * 
 * @Size(min = 3, message = "Estado : debe tener un minimo de 3 caracteres")
 * private String estado;
 * 
 * @Column(name = "motivo_anulacion", nullable = false, length = 250)
 * 
 * @Size(min = 5, message =
 * "Motivo de anulacion : debe tener un minimo de 5 caracteres") private String
 * motivoAnulacion;
 * 
 * @Column(name = "estado_envio_sunat", nullable = false, length = 12)
 * 
 * @Size(min = 3, message =
 * "Estado de envio a sunat : debe tener un minimo de 3 caracteres") private
 * String estadoEnvioSunat;
 * 
 * @Column(name = "nro_documento_adquiriente", nullable = false, length = 20)
 * 
 * @Size(min = 8, message =
 * "Nro de documento del adquiriente : debe tener un minimo de 8 caracteres")
 * private String nroDocumentoAdquiriente;
 * 
 * @Column(name = "correo_cliente", nullable = true, length = 60)
 * 
 * @Size(min = 5, message =
 * "Correo del cliente : debe tener un minimo de 5 caracteres") private String
 * correoCliente;
 * 
 * @Column(name = "total_valor_venta_op_gravadas", nullable = false) private
 * BigDecimal totalValorVentaOpGravadas; //1
 * 
 * @Column(name = "total_valor_venta_op_inafectas", nullable = false) private
 * BigDecimal totalValorVentaOpInafectas;//2
 * 
 * @Column(name = "total_valor_venta_op_exoneradas", nullable = false) private
 * BigDecimal totalValorVentaOpExoneradas;//3
 * 
 * @Column(name = "total_sumatoria_igv", nullable = false) private BigDecimal
 * totalSumatoriaIGV;//4
 * 
 * @Column(name = "total_sumatoria_isc", nullable = false) private BigDecimal
 * totalSumatoriaISC;//5
 * 
 * @Column(name = "total_vsumatoria_otros_tributos", nullable = false) private
 * BigDecimal totalSumatoriaOtrosTibutos;//6
 * 
 * @Column(name = "total_sumatoria_cargos", nullable = false) private BigDecimal
 * totalSumatoriaCargos;//7
 * 
 * @Column(name = "total_sumatoria_descuentos", nullable = false) private
 * BigDecimal totalSumatoriaDescuentos;
 * 
 * @Column(name = "total_importe_venta", nullable = false) private BigDecimal
 * totalImporteVenta;//Sumatoria de 1+2+3+4+5+6+7
 * 
 * @Column(name = "total_operaciones_gratuitas", nullable = false) private
 * BigDecimal totalOperacionesGratuitas;
 * 
 * @OneToMany(mappedBy = "venta", cascade = {CascadeType.PERSIST,
 * CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY,orphanRemoval
 * = true) private List<VentaDetalle> ventaDetalle; //private Usuario
 * usuarioReg; //private TipoComprobante tipoComprobante; //Boleta = 03, Factura
 * //private Caja caja; //private Beneficicio beneficio; //private FormaPago
 * formaPago; //private Dependencia dependencia //Dependencia que solicita o
 * consume el producto //private Almacen almacenOrigen; //private Usuario
 * usuarioAnula; //private Persona personaCliente; //DATOS PARA FACTURACION
 * ELECTRONICA //private Modeda moneda; //private TipoDocumento
 * tipoDocumentoIdentidadAdquiriente; //private Organizacion
 * organizacion;//Corresponde a los apellidos y nombres o denominación o razón
 * social del emisor de la boleta de venta electrónica. Este elemento debe ser
 * acorde a lo registrado en el Registro Único de Contribuyentes - RUC.
 * //private TipoCambio tipoCambio;
 * 
 * public List<VentaDetalle> getVentaDetalle() { return ventaDetalle; }
 * 
 * public void setVentaDetalle(List<VentaDetalle> ventaDetalle) {
 * this.ventaDetalle = ventaDetalle; }
 * 
 * public long getIdventa() { return idventa; }
 * 
 * public void setIdventa(long idventa) { this.idventa = idventa; }
 * 
 * public LocalDateTime getFechaEmision() { return fechaEmision; }
 * 
 * public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision =
 * fechaEmision; }
 * 
 * public String getSerieComprobante() { return serieComprobante; }
 * 
 * public void setSerieComprobante(String serieComprobante) {
 * this.serieComprobante = serieComprobante; }
 * 
 * public long getNroComprobante() { return nroComprobante; }
 * 
 * public void setNroComprobante(long nroComprobante) { this.nroComprobante =
 * nroComprobante; }
 * 
 * public String getEstado() { return estado; }
 * 
 * public void setEstado(String estado) { this.estado = estado; }
 * 
 * public String getMotivoAnulacion() { return motivoAnulacion; }
 * 
 * public void setMotivoAnulacion(String motivoAnulacion) { this.motivoAnulacion
 * = motivoAnulacion; }
 * 
 * public String getEstadoEnvioSunat() { return estadoEnvioSunat; }
 * 
 * public void setEstadoEnvioSunat(String estadoEnvioSunat) {
 * this.estadoEnvioSunat = estadoEnvioSunat; }
 * 
 * public String getNroDocumentoAdquiriente() { return nroDocumentoAdquiriente;
 * }
 * 
 * public void setNroDocumentoAdquiriente(String nroDocumentoAdquiriente) {
 * this.nroDocumentoAdquiriente = nroDocumentoAdquiriente; }
 * 
 * public String getCorreoCliente() { return correoCliente; }
 * 
 * public void setCorreoCliente(String correoCliente) { this.correoCliente =
 * correoCliente; }
 * 
 * public BigDecimal getTotalValorVentaOpGravadas() { return
 * totalValorVentaOpGravadas; }
 * 
 * public void setTotalValorVentaOpGravadas(BigDecimal
 * totalValorVentaOpGravadas) { this.totalValorVentaOpGravadas =
 * totalValorVentaOpGravadas; }
 * 
 * public BigDecimal getTotalValorVentaOpInafectas() { return
 * totalValorVentaOpInafectas; }
 * 
 * public void setTotalValorVentaOpInafectas(BigDecimal
 * totalValorVentaOpInafectas) { this.totalValorVentaOpInafectas =
 * totalValorVentaOpInafectas; }
 * 
 * public BigDecimal getTotalValorVentaOpExoneradas() { return
 * totalValorVentaOpExoneradas; }
 * 
 * public void setTotalValorVentaOpExoneradas(BigDecimal
 * totalValorVentaOpExoneradas) { this.totalValorVentaOpExoneradas =
 * totalValorVentaOpExoneradas; }
 * 
 * public BigDecimal getTotalSumatoriaIGV() { return totalSumatoriaIGV; }
 * 
 * public void setTotalSumatoriaIGV(BigDecimal totalSumatoriaIGV) {
 * this.totalSumatoriaIGV = totalSumatoriaIGV; }
 * 
 * public BigDecimal getTotalSumatoriaISC() { return totalSumatoriaISC; }
 * 
 * public void setTotalSumatoriaISC(BigDecimal totalSumatoriaISC) {
 * this.totalSumatoriaISC = totalSumatoriaISC; }
 * 
 * public BigDecimal getTotalSumatoriaOtrosTibutos() { return
 * totalSumatoriaOtrosTibutos; }
 * 
 * public void setTotalSumatoriaOtrosTibutos(BigDecimal
 * totalSumatoriaOtrosTibutos) { this.totalSumatoriaOtrosTibutos =
 * totalSumatoriaOtrosTibutos; }
 * 
 * public BigDecimal getTotalSumatoriaCargos() { return totalSumatoriaCargos; }
 * 
 * public void setTotalSumatoriaCargos(BigDecimal totalSumatoriaCargos) {
 * this.totalSumatoriaCargos = totalSumatoriaCargos; }
 * 
 * public BigDecimal getTotalSumatoriaDescuentos() { return
 * totalSumatoriaDescuentos; }
 * 
 * public void setTotalSumatoriaDescuentos(BigDecimal totalSumatoriaDescuentos)
 * { this.totalSumatoriaDescuentos = totalSumatoriaDescuentos; }
 * 
 * public BigDecimal getTotalImporteVenta() { return totalImporteVenta; }
 * 
 * public void setTotalImporteVenta(BigDecimal totalImporteVenta) {
 * this.totalImporteVenta = totalImporteVenta; }
 * 
 * public BigDecimal getTotalOperacionesGratuitas() { return
 * totalOperacionesGratuitas; }
 * 
 * public void setTotalOperacionesGratuitas(BigDecimal
 * totalOperacionesGratuitas) { this.totalOperacionesGratuitas =
 * totalOperacionesGratuitas; }
 * 
 * 
 * 
 * }
 */