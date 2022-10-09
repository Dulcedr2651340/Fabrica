package com.example.demo.model;

import java.util.Date;

public class ResponseMessage {

	private boolean respuesta;
	private String mensaje;
	private Date fecharespuesta;
	
	
	public ResponseMessage() {
	}
	public boolean isRespuesta() {
		return respuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public Date getFecharespuesta() {
		return fecharespuesta;
	}
	
	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setFecharespuesta(Date fecharespuesta) {
		this.fecharespuesta = fecharespuesta;
	}
	
	
	
	
}
