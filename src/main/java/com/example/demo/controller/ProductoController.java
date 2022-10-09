package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.model.ResponseMessage;
import com.example.demo.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAllProductos(){
		List<Producto> listDB = service.list();
		if(listDB.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 	
		}
		return new ResponseEntity<>(listDB, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable("id") Integer id){
		return new ResponseEntity<>(service.findById(id),HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity<ResponseMessage> postNewProducto(@RequestBody Producto producto){
		ResponseMessage res = service.insert(producto);
		if(res.isRespuesta()) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	}
	
	@PutMapping
	public ResponseEntity<ResponseMessage> putEditProducto(@RequestBody Producto producto){
		ResponseMessage res = service.update(producto);
		if(res.isRespuesta()) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteProducto(@PathVariable("id") Integer id){
		ResponseMessage res = service.delete(id);
		if(res.isRespuesta()) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
	}
}
