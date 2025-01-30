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

import com.kabat.photos.entity.Photo;
import com.kabat.photos.service.PhotoService;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/photosApi")
public class PhotoRestController {
	private static final Logger LOG = LogManager.getLogger(PhotoRestController.class.getName());
	
	@Autowired
	PhotoService service;
	
	@PostMapping("/photo")
	public ResponseEntity<Photo> altaPhoto(@RequestBody Photo in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Photo out = new Photo();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.altaPhoto(in);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Photo>(out,HttpStatus.CREATED);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Photo>(out,headers,exception.getStatusCode());
		}
	}

	@GetMapping("/photo")
	public ResponseEntity<List<Photo>> photo() {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	List<Photo> out = new ArrayList<Photo>();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getPhoto();
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<List<Photo>>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<List<Photo>>(out,headers,exception.getStatusCode());
		}
	}
	
	@GetMapping("/photo/{id}")
	public ResponseEntity<Photo> photo(@PathVariable("id") Integer id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	Photo out = new Photo();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.getPhoto(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Photo>(out,out!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Photo>(out,headers,exception.getStatusCode());
		}
	}
	
	@DeleteMapping("/photo/{id}")
	public ResponseEntity<Photo> deleteUser(@PathVariable("id") Integer id) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	int out;
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.deletePhoto(id);
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<Photo>(new Photo(),out==1?HttpStatus.NO_CONTENT:HttpStatus.NOT_FOUND);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<Photo>(new Photo(),headers,exception.getStatusCode());
		}
	}
}