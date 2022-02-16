package br.pro.brand.cursojavaspring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;

@SpringBootApplication
public class CursoJavaSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursoJavaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
