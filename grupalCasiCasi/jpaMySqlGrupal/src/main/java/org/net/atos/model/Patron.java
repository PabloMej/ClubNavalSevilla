package org.net.atos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patron_id")
	private Long id;
	@NotBlank(message = "Campo requerido")
	private String nombre;
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message="El DNI tiene que tener el formato 12345678A o 12345678a")
	private String dni;
	@NotBlank(message = "Campo requerido")
	@Pattern(regexp="[0-9]{9}", message="El telefono debe contener solo números")
	private String telefono;
	@NotBlank(message = "Campo requerido")
	private String email;
	private Boolean socio;
	
	public Patron(String nombre, String dni, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
	}

	public Patron(String nombre, String dni, String telefono, String email) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}
	
}
