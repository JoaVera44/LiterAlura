package com.literalura.LiterAlura;

import com.literalura.LiterAlura.principal.Principal;
import com.literalura.LiterAlura.repositorio.AutorRepository;
import com.literalura.LiterAlura.repositorio.LibroRepository;
import com.literalura.LiterAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {



	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroService libroService;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository, autorRepository, libroService);

		principal.muestraMenu();
	}
}
