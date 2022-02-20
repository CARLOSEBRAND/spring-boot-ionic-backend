package br.pro.brand.cursojavaspring.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.dto.CategoryDTO;
import br.pro.brand.cursojavaspring.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> search(@PathVariable Integer id) {
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

	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Category obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> searchAll() {
		List<Category> list = service.searchAll();
		List<CategoryDTO> listDTO = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> searchPage(
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@RequestParam(value = "lines", defaultValue = "24") Integer linesPerPage, 
		@RequestParam(value = "order", defaultValue = "description") String orderBy,
		@RequestParam(value = "dir", defaultValue = "ASC") String direction) {
		Page<Category> pageList = service.searchPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> pageListDTO = pageList.map(obj -> new CategoryDTO(obj));
		return ResponseEntity.ok().body(pageListDTO);
	}
}
