package br.pro.brand.cursojavaspring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.pro.brand.cursojavaspring.domain.Adress;
import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.domain.City;
import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.domain.Product;
import br.pro.brand.cursojavaspring.domain.State;
import br.pro.brand.cursojavaspring.domain.enums.CustomerType;
import br.pro.brand.cursojavaspring.repositories.AdressRepository;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;
import br.pro.brand.cursojavaspring.repositories.CityRepository;
import br.pro.brand.cursojavaspring.repositories.CustomerRepository;
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

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AdressRepository adressRepository;

@SpringBootApplication
public class CursoJavaSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
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

		Customer ctmr1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.FISICA);
		ctmr1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Adress a1 = new Adress(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", ctmr1, c1);
		Adress a2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", ctmr1, c2);

		//associating tables category x product
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2,c3));
		ctmr1.getAdresses().addAll(Arrays.asList(a1,a2));

		//save in tables
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		stateRepository.saveAll(Arrays.asList(est1,est2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		customerRepository.saveAll(Arrays.asList(ctmr1));
		adressRepository.saveAll(Arrays.asList(a1,a2));

	}

}
