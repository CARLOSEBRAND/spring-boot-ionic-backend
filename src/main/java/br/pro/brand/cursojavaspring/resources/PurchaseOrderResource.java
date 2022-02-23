package br.pro.brand.cursojavaspring.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pro.brand.cursojavaspring.domain.PurchaseOrder;
import br.pro.brand.cursojavaspring.services.PurchaseOrderService;

@RestController
@RequestMapping(value="/orders")
public class PurchaseOrderResource {

	@Autowired
	private PurchaseOrderService service;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<PurchaseOrder> search(@PathVariable Integer id) {
		PurchaseOrder obj = service.search(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PurchaseOrder obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
