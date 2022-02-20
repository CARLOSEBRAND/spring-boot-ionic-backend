package br.pro.brand.cursojavaspring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.services.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Customer> search(@PathVariable Integer id) {
		Customer obj = service.search(id);
		return ResponseEntity.ok().body(obj);
	}
}
