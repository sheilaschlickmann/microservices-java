package com.unibave.filaocorrencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FilaOcorrenciaApplication {

	public static void main(String[] args) {

		SpringApplication.run(FilaOcorrenciaApplication.class, args);
		//System.out.println("senha: " + new BCryptPasswordEncoder().encode("pastel"));
	}

}
