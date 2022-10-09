package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity(name = "fabrica")
public class Fabrica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_fabrica;
	private String sede;
	private String direccion;
	private String telefono;
	
	@OneToMany(mappedBy = "fabrica")
	private Collection<PiezaFabricada> itemsPiezasFabricadas = new ArrayList<>();
	
	public Fabrica() {
	}
	
	public Fabrica(Integer id_fabrica, String sede, String direccion, String telefono) {
		this.id_fabrica = id_fabrica;
		this.sede = sede;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Integer getId_fabrica() {
		return id_fabrica;
	}

	public String getSede() {
		return sede;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setId_fabrica(Integer id_fabrica) {
		this.id_fabrica = id_fabrica;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
