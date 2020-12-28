package com.wallet.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
	
	private Long id;
	
	
	
	@Email(message = "E-mail invalido!!")
	private String email;
	@Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caractéres")
	private String name;
	
	@NotNull
	@Length(min = 6, message = "A senha deve conter no minímo 6 caracteres!")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
