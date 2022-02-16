package br.pro.brand.cursojavaspring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Category obj = service.find_category(id);
		return ResponseEntity.ok().body(obj);
	}
}
