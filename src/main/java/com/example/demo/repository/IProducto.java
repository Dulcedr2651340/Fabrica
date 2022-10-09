package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Producto;

@Repository
public interface IProducto extends CrudRepository<Producto, Integer>{

}
