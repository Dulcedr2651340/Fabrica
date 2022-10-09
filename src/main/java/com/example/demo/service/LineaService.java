package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Linea;
import com.example.demo.model.ResponseMessage;
import com.example.demo.repository.ILinea;

@Repository
public class LineaService {

	@Autowired
	private ILinea repository;
	
	public List<Linea> list(){
		return (List<Linea>) repository.findAll();
	}
	
	public Linea findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	public ResponseMessage insert(Linea l){
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			repository.save(l);
			mensaje  = "Linea registrada correctamente";
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
	
	public ResponseMessage update(Linea l) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			Linea objDB = repository.findById(l.getId_linea()).orElse(null);
			if(objDB == null) {
				mensaje  = "No existe un registro con el id: "+l.getId_linea();
				respuesta = false;				
			}else {
				repository.save(l);
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
			mensaje  = "Linea eliminada correctamente";
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
