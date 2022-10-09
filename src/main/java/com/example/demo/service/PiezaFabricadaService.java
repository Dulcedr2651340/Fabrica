package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Fabrica;
import com.example.demo.model.PiezaFabricada;
import com.example.demo.model.Producto;
import com.example.demo.model.ResponseMessage;
import com.example.demo.repository.IFabrica;
import com.example.demo.repository.IPiezaFabricada;
import com.example.demo.repository.IProducto;

@Service
public class PiezaFabricadaService {

	@Autowired
	private IPiezaFabricada repository;
	
	@Autowired
	private IProducto repositoryP;
	
	@Autowired
	private IFabrica repositoryF;
	
	public List<PiezaFabricada> list(){
		return (List<PiezaFabricada>) repository.findAll();
	}
	
	public PiezaFabricada findById(Integer id){
		return repository.findById(id).orElse(null);
	}
	
	public ResponseMessage insert(PiezaFabricada p){
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			Producto objPDB = repositoryP.findById(p.getProducto().getId_producto()).orElse(null);
			Fabrica objFDB = repositoryF.findById(p.getFabrica().getId_fabrica()).orElse(null);
			if(objPDB==null) {
				mensaje  = "No existe un registro de Producto con el id: "+p.getProducto().getId_producto();
				respuesta = false;	
			}else if( objFDB==null) {
				mensaje  = "No existe un registro de Fabrica con el id: "+p.getFabrica().getId_fabrica();
				respuesta = false;	
			}else {
				repository.save(p);
				mensaje  = "Piezas registradas correctamente";
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
	
	public ResponseMessage update(PiezaFabricada p) {
		ResponseMessage resultado = new ResponseMessage();
		String mensaje = "";
		boolean respuesta = false;				
		try {
			PiezaFabricada objDB = repository.findById(p.getId_pieza_fabricada()).orElse(null);
			if(objDB == null) {
				mensaje  = "No existe un registro de Piezas Fabricadas con el id: "+p.getId_pieza_fabricada();
				respuesta = false;				
			}/*else if(objPDB==null) {
				mensaje  = "No existe un registro de Producto con el id: "+p.getProducto().getId_producto();
				respuesta = false;	
			}else if( objFDB==null) {
				mensaje  = "No existe un registro de Fabrica con el id: "+p.getFabrica().getId_fabrica();
				respuesta = false;	
			}*/else {
				if(p.getCantidad()==null) p.setCantidad(objDB.getCantidad());
				if(p.getFecha_fabricacion()==null) p.setFecha_fabricacion(objDB.getFecha_fabricacion());
				if(p.getFabrica()==null) p.setFabrica(objDB.getFabrica());	
				if(p.getProducto()==null) p.setProducto(objDB.getProducto());
				repository.save(p);
				mensaje  = " Piezas Fabricadas actualizadas correctamente";
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
			mensaje  = "Piezas Fabricadas eliminadas correctamente";
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
	
	public List<PiezaFabricada> consultaPorFecha(java.sql.Date fechaI, java.sql.Date fechaF){
		return repository.consultarPorFechas(fechaI, fechaF);
	}
}
