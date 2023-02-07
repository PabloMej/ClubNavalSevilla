package org.net.atos.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Barco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="Campo requerido")
	@Pattern(regexp="[A-Z]{3}[0-9]{3}", message="El formato de la matricula debe ser de tres letras mayusculas y tres numeros EJ: ABC123")
	private String matricula;
	@NotBlank(message="Campo requerido")
	private String nombre;
	@NotNull(message="Campo requerido")
	@Min(value=1)
	private Double cuota;
	@OneToMany(mappedBy = "barco")
	private List<Salida>salidas = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "socio_id")
	private Socio socio;
	@NotNull(message="Campo requerido")
	private Integer amarre;
	
	public Barco(String matricula, String nombre, Double cuota, Integer amarre) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.cuota = cuota;
		this.amarre = amarre;
	}

	public Barco(String matricula, String nombre, Double cuota, Socio socio, Integer amarre) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.cuota = cuota;
		this.socio = socio;
		this.amarre = amarre;
	}
}
