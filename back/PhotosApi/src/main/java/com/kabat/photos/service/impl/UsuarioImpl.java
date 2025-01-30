package com.kabat.photos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabat.photos.entity.Usuario;
import com.kabat.photos.repository.UsuarioRepository;
import com.kabat.photos.service.UsuarioService;

@Service("UsuarioService")
public class UsuarioImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public Usuario altaUsuario(Usuario in) throws Exception {
		try {
			return repository.save(in);
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}		
	}

	@Override
	public List<Usuario> getUsuario() throws Exception {
		try {
			return repository.findAll();
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
	}

	@Override
	public Usuario getUsuario(Integer id) throws Exception{
		try {
			Optional<Usuario> out = repository.findById(id);
			if(out.isPresent()) {
				return out.get();
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return null;
	}

	@Override
	public int deleteUsuario(Integer id) throws Exception {
		try {
			Optional<Usuario> out = repository.findById(id);
			if(out.isPresent()) {
				repository.deleteById(id);
				return 1;
			}
		}catch (Exception e) {
			throw new Exception(this.getClass().getName() + " : " + e.getLocalizedMessage());
		}	
		return 0;
	}
}