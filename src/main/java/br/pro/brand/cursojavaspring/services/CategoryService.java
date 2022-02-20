package br.pro.brand.cursojavaspring.services;

import java.util.List;
import java.util.Optional;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.Category;
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
        search(obj.getId());
        return rep.save(obj);
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
    
}
