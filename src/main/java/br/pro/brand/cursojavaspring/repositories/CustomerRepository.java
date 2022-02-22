package br.pro.brand.cursojavaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pro.brand.cursojavaspring.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
    @Transactional(readOnly=true)
	Customer findByEmail(String email);

}
