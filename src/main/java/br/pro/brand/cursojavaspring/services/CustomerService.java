package br.pro.brand.cursojavaspring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.exceptions.ObjectNotFoundException;
import br.pro.brand.cursojavaspring.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository rep;

    public Customer search(Integer id) {
        Optional<Customer> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + 
            " ,Type: " + Customer.class.getName(), null)
        );
    }
    
}
