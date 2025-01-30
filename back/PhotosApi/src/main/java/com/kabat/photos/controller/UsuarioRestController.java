package com.kabat.photos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.kabat.photos.entity.Usuario;
import com.kabat.photos.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/photosApi")
public class UsuarioRestController {
	private static final Logger LOG = LogManager.getLogger(UsuarioRestController.class.getName());
	
	@Autowired
	UsuarioService service;
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> altaUser(@RequestBody Usuario in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Usuario out = new Usuario();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.altaUsuario(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Usuario>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Usuario>(out,headers,exception.getStatusCode());
		}
	}

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> user() {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	List<Usuario> out = new ArrayList<Usuario>();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getUsuario();
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<List<Usuario>>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<List<Usuario>>(out,headers,exception.getStatusCode());
		}
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> user(@PathVariable("id") Integer id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Usuario out = new Usuario();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getUsuario(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Usuario>(out,out!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Usuario>(out,headers,exception.getStatusCode());
		}
	}
	
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<Usuario> deleteUser(@PathVariable("id") Integer id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	int out;
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.deleteUsuario(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Usuario>(new Usuario(),out==1?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Usuario>(new Usuario(),headers,exception.getStatusCode());
		}
	}
}