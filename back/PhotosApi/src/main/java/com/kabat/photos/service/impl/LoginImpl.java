package com.kabat.photos.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabat.photos.entity.Usuario;
import com.kabat.photos.model.LoginOutDto;
import com.kabat.photos.repository.UsuarioRepository;
import com.kabat.photos.service.LoginService;

@Service("LoginService")
public class LoginImpl implements LoginService {
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public LoginOutDto validaUsuario(String email, String pass) throws Exception {
		LoginOutDto out = new LoginOutDto(false);
		try {
			Optional<Usuario> user = repository.findByEmail(email);
			if (user.isPresent()) {
				Usuario x = user.get();
				if(pass.equals(x.getPass())) {
					out.setValid(true);
					out.setNombre(x.getNombre());
				}
			}
			
			return out;
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}
}