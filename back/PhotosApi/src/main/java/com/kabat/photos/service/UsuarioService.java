package com.kabat.photos.service;

import java.util.List;

import com.kabat.photos.entity.Usuario;

public interface UsuarioService {
	public Usuario altaUsuario(Usuario in)throws Exception;
	public List<Usuario> getUsuario()throws Exception;
	public Usuario getUsuario(Integer id)throws Exception;
	public int deleteUsuario(Integer id) throws Exception;
}