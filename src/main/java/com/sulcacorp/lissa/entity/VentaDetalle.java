/*
 * package com.sulcacorp.lissa.model;
 * 
 * import java.math.BigDecimal;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.ForeignKey; import javax.persistence.GeneratedValue; import
 * javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToOne; import
 * javax.persistence.Table;
 * 
 * import com.fasterxml.jackson.annotation.JsonIgnore;
 * 
 * //@Entity //@Table(name = "venta_detalle") public class VentaDetalle {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private long
 * idventadetalle;
 * 
 * //private AlmacenProducto almacenProducto;
 * 
 * @JsonIgnore
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "id_venta", nullable = false, foreignKey
 * = @ForeignKey(name="fk_ventadetalle_venta")) private Venta venta;
 * 
 * @Column(name = "cantidad_venta", nullable = false) private BigDecimal
 * cantidadVenta; //private UnidadEquivalente //unidad medida equivalente a la
 * venta //private TipoIgv tipoIGV;
 * 
 * @Column(name = "valor_unitario_item", nullable = false) private BigDecimal
 * valorUnitarioItem;
 * 
 * @Column(name = "precio_unitario_item", nullable = false) private BigDecimal
 * precioUnitarioItem;
 * 
 * @Column(name = "sub_total", nullable = false) private BigDecimal subTotal;
 * 
 * @Column(name = "monto_igv_item", nullable = false) private BigDecimal
 * montoIgvItem;
 * 
 * @Column(name = "monto_total", nullable = false) private BigDecimal
 * montoTotal;
 * 
 * public long getIdventadetalle() { return idventadetalle; }
 * 
 * public void setIdventadetalle(long idventadetalle) { this.idventadetalle =
 * idventadetalle; }
 * 
 * public Venta getVenta() { return venta; }
 * 
 * public void setVenta(Venta venta) { this.venta = venta; }
 * 
 * public BigDecimal getCantidadVenta() { return cantidadVenta; }
 * 
 * public void setCantidadVenta(BigDecimal cantidadVenta) { this.cantidadVenta =
 * cantidadVenta; }
 * 
 * public BigDecimal getValorUnitarioItem() { return valorUnitarioItem; }
 * 
 * public void setValorUnitarioItem(BigDecimal valorUnitarioItem) {
 * this.valorUnitarioItem = valorUnitarioItem; }
 * 
 * public BigDecimal getPrecioUnitarioItem() { return precioUnitarioItem; }
 * 
 * public void setPrecioUnitarioItem(BigDecimal precioUnitarioItem) {
 * this.precioUnitarioItem = precioUnitarioItem; }
 * 
 * public BigDecimal getSubTotal() { return subTotal; }
 * 
 * public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
 * 
 * public BigDecimal getMontoIgvItem() { return montoIgvItem; }
 * 
 * public void setMontoIgvItem(BigDecimal montoIgvItem) { this.montoIgvItem =
 * montoIgvItem; }
 * 
 * public BigDecimal getMontoTotal() { return montoTotal; }
 * 
 * public void setMontoTotal(BigDecimal montoTotal) { this.montoTotal =
 * montoTotal; } }
 */