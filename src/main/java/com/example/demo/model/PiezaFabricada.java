package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pieza_fabricada")
public class PiezaFabricada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_pieza_fabricada;
	private Integer cantidad;
	private Date fecha_fabricacion;
	
	@ManyToOne
	@JoinColumn(name = "id_fabrica", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_fabrica) references fabrica(id_fabrica)"))
	private Fabrica fabrica;
	
	@ManyToOne
	@JoinColumn(name = "id_producto", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto) references producto(id_producto)"))
	private Producto producto;

	public PiezaFabricada() {
	}
	
	public PiezaFabricada(Integer id_pieza_fabricada, Integer cantidad, Date fecha_fabricacion, Fabrica fabrica,
			Producto producto) {
		this.id_pieza_fabricada = id_pieza_fabricada;
		this.cantidad = cantidad;
		this.fecha_fabricacion = fecha_fabricacion;
		this.fabrica = fabrica;
		this.producto = producto;
	}

	public Integer getId_pieza_fabricada() {
		return id_pieza_fabricada;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public Date getFecha_fabricacion() {
		return fecha_fabricacion;
	}

	public Fabrica getFabrica() {
		return fabrica;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setId_pieza_fabricada(Integer id_pieza_fabricada) {
		this.id_pieza_fabricada = id_pieza_fabricada;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setFecha_fabricacion(Date fecha_fabricacion) {
		this.fecha_fabricacion = fecha_fabricacion;
	}

	public void setFabrica(Fabrica fabrica) {
		this.fabrica = fabrica;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
