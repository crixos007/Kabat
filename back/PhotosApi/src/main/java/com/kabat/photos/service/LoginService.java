package com.kabat.photos.service;

import com.kabat.photos.model.LoginOutDto;

public interface LoginService {
	public LoginOutDto validaUsuario(String email, String pass)throws Exception;
}