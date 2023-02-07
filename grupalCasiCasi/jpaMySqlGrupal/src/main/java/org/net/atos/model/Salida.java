package org.net.atos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Salida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="Campo requerido")
	private LocalDateTime fechaHoraSalida;
	@NotBlank(message="Campo requerido")
	private String destino;
	@ManyToOne
	@JoinColumn(name="patron_id")
	private Patron patron;
	@ManyToOne
	@JoinColumn(name="salidas_id")
	private Barco barco;
	
	public Salida(LocalDateTime fechaHoraSalida, String destino, Patron patron) {
		this.fechaHoraSalida = fechaHoraSalida;
		this.destino = destino;
		this.patron = patron;
	}
	
	public Salida(LocalDateTime fechaHoraSalida, String destino) {
		this.fechaHoraSalida = fechaHoraSalida;
		this.destino = destino;
	}
	

}
