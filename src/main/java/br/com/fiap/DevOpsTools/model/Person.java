package br.com.fiap.DevOpsTools.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Person {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Nome não pode se nulo/ vazio")
	private String name;
	private String address;
	@Size(max=15, min=11, message="O telefone deve ter entre 11 a 13 caracteres ex: (00) 00000-0000")
	private String phone;
	private String birthdate;
	
	private String sex;

}
