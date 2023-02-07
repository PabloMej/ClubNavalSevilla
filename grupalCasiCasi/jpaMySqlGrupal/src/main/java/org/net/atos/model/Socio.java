package org.net.atos.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo requerido")
	@Size(min = 3, message = "El nombre debe contener al menos 3 letras")
	private String nombre;
	@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El DNI tiene que tener el formato 12345678A o 12345678a")
	private String dni;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate nacimiento;
	@NotBlank(message = "Campo requerido")
	private String email;
	@NotBlank(message = "Campo requerido")
	@Pattern(regexp = "[0-9]{9}", message = "El telefono debe contener solo números")
	private String telefono;
	// suponiendo que un barco tiene solo un socio
	@OneToMany(mappedBy = "socio")
	private List<Barco> barcos = new ArrayList<>();
	private Boolean patron;

	public Socio(String nombre, String dni, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
	}
	
	public Socio(String nombre, String dni, String telefono, String email) {
		this.nombre = nombre;
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}

	public Socio(
			@NotNull(message = "Campo requerido") @Size(min = 3, message = "El nombre debe contener al menos 3 letras") String nombre,
			@Pattern(regexp = "[0-9]{8}[A-Za-z]{1}", message = "El DNI tiene que tener el formato 12345678A o 12345678a") String dni,
			LocalDate nacimiento, @NotBlank(message = "Campo requerido") String email,
			@NotEmpty(message = "Campo requerido") @Pattern(regexp = "[0-9]{9}", message = "El telefono debe contener solo números") String telefono, Boolean patron) {
		this.nombre = nombre;
		this.dni = dni;
		this.nacimiento = nacimiento;
		this.email = email;
		this.telefono = telefono;
		this.patron = patron;
	}
	
	

}
