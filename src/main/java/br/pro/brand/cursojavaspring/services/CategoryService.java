package br.pro.brand.cursojavaspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.Category;
import br.pro.brand.cursojavaspring.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository rep;

    public Category find_category(Integer id) {
        Optional<Category> obj = rep.findById(id);
        return obj.orElse(null);
    }
    
}
