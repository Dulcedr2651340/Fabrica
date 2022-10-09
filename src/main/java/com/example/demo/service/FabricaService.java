package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Fabrica;
import com.example.demo.model.ResponseMessage;
import com.example.demo.repository.IFabrica;

@Service
public class FabricaService {

	@Autowired
	private IFabrica repository;
	
	public List<Fabrica> list(){
		return (List<Fabrica>) repository.findAll();
	}
	
	public Fabrica findById(Integer id){
		return repository.findById(id).orElse(null);
	}
	
	public Fabrica findBySede(String sede){
		return repository.findBySede(sede).orElse(null);
	}
	
	public ResponseMessage insert(Fabrica f){
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			repository.save(f);
			mensaje  = "Fabrica registrada correctamente";
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
	
	public ResponseMessage update(Fabrica f) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			Fabrica objDB = repository.findById(f.getId_fabrica()).orElse(null);
			if(objDB == null) {
				mensaje  = "No existe un registro de fabrica con el id: "+f.getId_fabrica();
				respuesta = false;				
			}else {
				if(f.getDireccion()==null) f.setDireccion(objDB.getDireccion());
				if(f.getSede()==null) f.setSede(objDB.getSede());
				if(f.getTelefono()==null) f.setTelefono(objDB.getTelefono());		
				repository.save(f);
				mensaje  = "Fabrica actualizada correctamente";
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
			mensaje  = "Fabrica eliminada correctamente";
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
