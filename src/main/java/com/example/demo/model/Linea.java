package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "linea")
public class Linea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_linea;
	
	@Column(name = "tipo")
	private String tipo;
	
	@OneToMany(mappedBy = "linea")
	private Collection<Producto> itemsProductos = new ArrayList<>();
	
	public Linea() { 
	}

	public Linea(Integer id_linea, String tipo) {
		this.id_linea = id_linea;
		this.tipo = tipo;
	}

	public Integer getId_linea() {
		return id_linea;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId_linea(Integer id_linea) {
		this.id_linea = id_linea;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
