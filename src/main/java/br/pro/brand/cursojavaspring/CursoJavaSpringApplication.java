package br.pro.brand.cursojavaspring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.domain.City;
import br.pro.brand.cursojavaspring.domain.Product;
import br.pro.brand.cursojavaspring.domain.State;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;
import br.pro.brand.cursojavaspring.repositories.CityRepository;
import br.pro.brand.cursojavaspring.repositories.ProductRepository;
import br.pro.brand.cursojavaspring.repositories.StateRepository;

@SpringBootApplication
public class CursoJavaSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursoJavaSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//adding data in objects
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 70.00);

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		//associating tables category x product
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2,c3));

		//save in tables
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		stateRepository.saveAll(Arrays.asList(est1,est2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));		
	}

}
