package com.kabat.photos.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.kabat.photos.model.LoginInDto;
import com.kabat.photos.model.LoginOutDto;
import com.kabat.photos.service.LoginService;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/photosApi/login")
public class LoginRestController {
	private static final Logger LOG = LogManager.getLogger(LoginRestController.class.getName());
	
	@Autowired
	LoginService service;
	
	@PostMapping("/valido")
	public ResponseEntity<LoginOutDto> validaUser(@RequestBody LoginInDto in) {
		Date date = new Date();		
    	String idTraza = (int)(Math.random()*30+1) + date.getTime() + "_LOG ";	
    	LoginOutDto out = new LoginOutDto();
		
		//LOGICA DEL SERVICIO
		try {	
			try {
				out = service.validaUsuario(in.getEmail(), in.getPass());
			}catch (Exception e) {
				LOG.error(idTraza +" ERROR MSG: " + e.getLocalizedMessage());
				throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<LoginOutDto>(out,HttpStatus.OK);
		}
		catch(HttpStatusCodeException exception) {
			LOG.error(idTraza +" ERROR MSG: " + exception.getLocalizedMessage());
			HttpHeaders headers=new HttpHeaders();
			headers.add("error", exception.getLocalizedMessage());
			headers.add("errorDesc", exception.getResponseBodyAsString());
			return new ResponseEntity<LoginOutDto>(out,headers,exception.getStatusCode());
		}
	}
}