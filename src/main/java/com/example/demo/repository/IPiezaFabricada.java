package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PiezaFabricada;

@Repository
public interface IPiezaFabricada extends CrudRepository<PiezaFabricada, Integer>{
	
	@Query(value = "select * from pieza_fabricada where (fecha_fabricacion BETWEEN :fechaI AND :fechaF)", nativeQuery = true)
	List<PiezaFabricada> consultarPorFechas(@Param("fechaI") Date fechaI, @Param("fechaF") Date fechaF);
}
