package com.kabat.photos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginOutDto {
	private String nombre;
	private boolean isValid;
	
	/**
	 * @param isValid
	 */
	public LoginOutDto(boolean isValid) {
		super();
		this.isValid = isValid;
	}	
}