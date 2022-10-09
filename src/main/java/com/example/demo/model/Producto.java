package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_producto;
	private String nombre;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_linea", nullable = false,
	foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_linea) references linea(id_linea)"))
	private Linea linea;
	
	@OneToMany(mappedBy = "producto")
	private Collection<PiezaFabricada> itemsPiezasFabricadas = new ArrayList<>();
	
	public Producto() {
	}

	public Producto(Integer id_producto, String nombre, String descripcion, Linea linea) {
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.linea = linea;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}
	
}
