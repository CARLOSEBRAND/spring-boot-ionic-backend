package br.pro.brand.cursojavaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pro.brand.cursojavaspring.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    
}
