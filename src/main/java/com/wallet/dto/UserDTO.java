package com.wallet.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	@Email(message = "E-mail invalido!!")
	private String email;
	@Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caractéres")
	private String name;
	
	@NotNull
	@Length(min = 6, message = "A senha deve conter no minímo 6 caracteres!")
	private String password;
	

}
