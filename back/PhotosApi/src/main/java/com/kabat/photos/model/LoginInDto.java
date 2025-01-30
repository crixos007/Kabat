package com.kabat.photos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInDto {
	private String email;
	private String pass;
}