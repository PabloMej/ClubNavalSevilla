package org.net.atos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.net.atos.model.Barco;
import org.net.atos.model.Patron;
import org.net.atos.model.Salida;
import org.net.atos.model.Socio;
import org.net.atos.repository.BarcoRepository;
import org.net.atos.repository.PatronRepository;
import org.net.atos.repository.SalidaRepository;
import org.net.atos.repository.SocioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaMySqlApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(SocioRepository repoSocio, BarcoRepository repoBarco, PatronRepository repoPatron,
			SalidaRepository repoSalida) {
		Socio socio1 = new Socio("Pepito Palotes", "35625845L",LocalDate.of(1999, 12, 12),"pepito@gmail.com", "666556655", false);
		Socio socio2 = new Socio("Paquita Salas", "35625845L", LocalDate.of(1999, 11, 12),"paquita@gmail.com", "666588655", false);
		Socio socio3 = new Socio("Manuela Ayala", "35625845L", LocalDate.of(1999, 10, 12),"manuela@gmail.com", "666588655", false);

		Barco barco = new Barco("ASD654", "Poseidón", 34.0, socio1, 2);
		Barco barco2 = new Barco("QWE987", "Mar abierto", 50.0, 34);
		Barco barco3 = new Barco("RTY321", "Mar azul", 54.0, 19);

		Patron patron = new Patron("Capitán Pescanova", "65321545M", "654654565", "pescanova@gmail.com");
		Patron patron2 = new Patron("Maritere La Marinera", "54128555L", "696969696", "maritere@gmail.com");
		Patron patron3 = new Patron("Capitán Pescadilla", "99998878K", "635698569", "pescadilla@gmail.com");
		
		Salida salida = new Salida(LocalDateTime.of(2023, 8, 12, 12, 12), "Marbella", patron);

		return (args) -> {
			repoSocio.saveAll(Arrays.asList(socio1, socio2, socio3));
			repoBarco.saveAll(Arrays.asList(barco, barco2, barco3));
			repoPatron.saveAll(Arrays.asList(patron, patron3, patron2));
			repoSalida.save(salida);
		};
	}

}
