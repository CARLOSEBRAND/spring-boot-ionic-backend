package br.pro.brand.cursojavaspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.dto.CategoryDTO;
import br.pro.brand.cursojavaspring.exceptions.DataIntegrityException;
import br.pro.brand.cursojavaspring.exceptions.ObjectNotFoundException;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository rep;

    public Category search(Integer id) {
        Optional<Category> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + 
            " ,Type: " + Category.class.getName(), null)
        );
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return rep.save(obj);
    }

    public Category update(Category obj) {
		Category newObj = search(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}
    
    public void delete(Integer id) {
        search(id);
        try {
            rep.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Its not possible to delete a category that contains products");
        }
    }

    public List<Category> searchAll() {
        return rep.findAll();
    }

    public Page<Category> searchPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return rep.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO objDTO){
        return new Category(objDTO.getId(), objDTO.getDescription());
    }

    private void updateData(Category newObj, Category obj) {
		newObj.setDescription(obj.getDescription());
	}
    
}
