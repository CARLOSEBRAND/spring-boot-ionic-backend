package br.pro.brand.cursojavaspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.exceptions.ObjectNotFoundException;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository rep;

    public Category find_category(Integer id) {
        Optional<Category> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + 
            " ,Type: " + Category.class.getName(), null)
        );
    }
    
}
