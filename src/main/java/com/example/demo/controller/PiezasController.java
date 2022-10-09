package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.PiezaFabricada;
import com.example.demo.model.ResponseMessage;
import com.example.demo.service.PiezaFabricadaService;

@RestController
@RequestMapping("/piezas")
public class PiezasController {

	@Autowired
	private PiezaFabricadaService service;
	
	@GetMapping
	public ResponseEntity<List<PiezaFabricada>> getAllPiezas(){
		List<PiezaFabricada> listDB = service.list();
		if(listDB.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 	
		}
		return new ResponseEntity<>(listDB, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PiezaFabricada> getPiezasById(@PathVariable("id") Integer id){
		return new ResponseEntity<>(service.findById(id),HttpStatus.OK); 
	}
	
	@GetMapping("/consultarPorFecha/{fechaI}/{fechaF}")
	public ResponseEntity<List<PiezaFabricada>> consultarPorFechas(@PathVariable("fechaI") java.sql.Date fechaI, @PathVariable("fechaF") java.sql.Date fechaF){
		return new ResponseEntity<>(service.consultaPorFecha(fechaI,fechaF), HttpStatus.OK);
	}
	
	@GetMapping("/consultarPorFecha2/")
	public ResponseEntity<List<PiezaFabricada>> consultarPorFechas2(@RequestParam("fechaI") java.sql.Date fechaI , @RequestParam("fechaF") java.sql.Date fechaF){
		System.out.println("FECHA INICIO, "+fechaI);
		return new ResponseEntity<>(service.consultaPorFecha(fechaI,fechaF), HttpStatus.OK);
	}
		
	@PostMapping
	public ResponseEntity<ResponseMessage> postNewPiezas(@RequestBody PiezaFabricada piezas){
		ResponseMessage res = service.insert(piezas);
		if(res.isRespuesta()) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	public ResponseEntity<ResponseMessage> putEditPiezas(@RequestBody PiezaFabricada piezas){
		ResponseMessage res = service.update(piezas);
		if(res.isRespuesta()) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteFabrica(@PathVariable("id") Integer id){
		ResponseMessage res = service.delete(id);
		if(res.isRespuesta()) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	}
}
