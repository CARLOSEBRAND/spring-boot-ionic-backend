package br.pro.brand.cursojavaspring.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = service.search(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category obj) {
		obj = service.insert(obj);
		Integer uriVariables = obj.getId();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(uriVariables).toUri();
		return ResponseEntity.created(uri).build();
	}
}
