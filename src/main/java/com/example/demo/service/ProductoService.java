package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Linea;
import com.example.demo.model.Producto;
import com.example.demo.model.ResponseMessage;
import com.example.demo.repository.ILinea;
import com.example.demo.repository.IProducto;

@Service
public class ProductoService {

	@Autowired
	private IProducto repository;
	
	@Autowired
	private ILinea repositoryL;
	
	public List<Producto> list(){
		return (List<Producto>) repository.findAll();
	}
	
	public Producto findById(Integer id){
		return repository.findById(id).orElse(null);
	}
	
	public ResponseMessage insert(Producto p){
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			repository.save(p);
			mensaje  = "Producto registrado correctamente";
			respuesta = true;			
		} catch (Exception e) {
			mensaje = e.getMessage();
			respuesta = false;
		}
		resultado.setFecharespuesta(new Date());
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		return resultado;
	}  
	
	public ResponseMessage update(Producto p) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			Producto objDB = repository.findById(p.getId_producto()).orElse(null);
			//Linea objLDB = repositoryL.findById(p.getLinea().getId_linea()).orElse(null);
			if(objDB == null) {
				mensaje  = "No existe un registro de producto con el id: "+p.getId_producto();
				respuesta = false;				
			}/*else if(objLDB==null) {
				mensaje  = "No existe un registro de linea con el id: "+p.getLinea().getId_linea();
				respuesta = false;	
			}*/else {
				if(p.getNombre()==null) p.setNombre(objDB.getNombre());
				if(p.getDescripcion()==null) p.setDescripcion(objDB.getDescripcion());
				if(p.getLinea()==null) p.setLinea(objDB.getLinea());		
				repository.save(p);
				mensaje  = "Linea actualizada correctamente";
				respuesta = true;	
			}		
		} catch (Exception e) {
			mensaje = e.getMessage();
			respuesta = false;
		}
		resultado.setFecharespuesta(new Date());
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		return resultado;
	}
	
	public ResponseMessage delete(Integer id) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			repository.deleteById(id);
			mensaje  = "Producto eliminado correctamente";
			respuesta = true;			
		} catch (Exception e) {
			mensaje = e.getMessage();
			respuesta = false;
		}
		resultado.setFecharespuesta(new Date());
		resultado.setMensaje(mensaje);
		resultado.setRespuesta(respuesta);
		return resultado;
	} 
}
