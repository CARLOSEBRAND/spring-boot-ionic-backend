package br.pro.brand.cursojavaspring.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.Adress;
import br.pro.brand.cursojavaspring.domain.BankSlipPayment;
import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.domain.City;
import br.pro.brand.cursojavaspring.domain.CreditCardPayment;
import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.domain.OrderDetail;
import br.pro.brand.cursojavaspring.domain.Payment;
import br.pro.brand.cursojavaspring.domain.Product;
import br.pro.brand.cursojavaspring.domain.PurchaseOrder;
import br.pro.brand.cursojavaspring.domain.State;
import br.pro.brand.cursojavaspring.domain.enums.CustomerType;
import br.pro.brand.cursojavaspring.domain.enums.PaymentStatus;
import br.pro.brand.cursojavaspring.repositories.AdressRepository;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;
import br.pro.brand.cursojavaspring.repositories.CityRepository;
import br.pro.brand.cursojavaspring.repositories.CustomerRepository;
import br.pro.brand.cursojavaspring.repositories.OrderDetailRepository;
import br.pro.brand.cursojavaspring.repositories.PaymentRepository;
import br.pro.brand.cursojavaspring.repositories.ProductRepository;
import br.pro.brand.cursojavaspring.repositories.PurchaseOrderRepository;
import br.pro.brand.cursojavaspring.repositories.StateRepository;

@Service
public class DBService {

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

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

    public void instantiateTestDatabase() throws ParseException {

        //adding data in objects
		Category cat1 = new Category(null, "Inform??tica");
		Category cat2 = new Category(null, "Escrit??rio");
		Category cat3 = new Category(null, "Cama, mesa e Banho");
		Category cat4 = new Category(null, "Eletr??nicos");
		Category cat5 = new Category(null, "Jardinagem");
		Category cat6 = new Category(null, "Decora????o");
		Category cat7 = new Category(null, "Ferramentas");
		
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 70.00);
		Product p4 = new Product(null, "Mesa de escrit??rio", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Ro??adeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "S??o Paulo");

		City c1 = new City(null, "Uberl??ndia", est1);
		City c2 = new City(null, "S??o Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		Customer ctmr1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.FISICA);
		ctmr1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Adress a1 = new Adress(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", ctmr1, c1);
		Adress a2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", ctmr1, c2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		PurchaseOrder ped1 = new PurchaseOrder(null, sdf.parse("17/02/2022 02:29"), ctmr1, a1);
		PurchaseOrder ped2 = new PurchaseOrder(null, sdf.parse("17/02/2022 02:30"), ctmr1, a2);

		Payment pag1 = new CreditCardPayment(null, PaymentStatus.QUITADO, ped1, 6);
		ped1.setPayment(pag1);

		Payment pag2 = new BankSlipPayment(null, PaymentStatus.PENDENTE, ped2, sdf.parse("16/02/2022 17:55"), null);
		ped2.setPayment(pag2);

		OrderDetail ip1 = new OrderDetail(ped1, p1, 0.00, 1, 2000.00);
		OrderDetail ip2 = new OrderDetail(ped1, p3, 0.00, 2, 80.00);
		OrderDetail ip3 = new OrderDetail(ped2, p2, 100.00, 1, 800.00);

		//associating tables category x product
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2,c3));
		
		ctmr1.getAdresses().addAll(Arrays.asList(a1,a2));
		ctmr1.getPurchaseOrders().addAll(Arrays.asList(ped1,ped2));
		
		ped1.getOrderDetails().addAll(Arrays.asList(ip1, ip2));
		ped2.getOrderDetails().addAll(Arrays.asList(ip3));
		
		p1.getOrderDetails().addAll(Arrays.asList(ip1));
		p2.getOrderDetails().addAll(Arrays.asList(ip3));
		p3.getOrderDetails().addAll(Arrays.asList(ip2));

		//save in tables
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		stateRepository.saveAll(Arrays.asList(est1,est2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		customerRepository.saveAll(Arrays.asList(ctmr1));
		adressRepository.saveAll(Arrays.asList(a1,a2));
		purchaseOrderRepository.saveAll(Arrays.asList(ped1,ped2));
		paymentRepository.saveAll(Arrays.asList(pag1,pag2));

		orderDetailRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

    }
    
}
