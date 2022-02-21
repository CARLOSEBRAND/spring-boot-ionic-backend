package br.pro.brand.cursojavaspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.pro.brand.cursojavaspring.domain.Adress;
import br.pro.brand.cursojavaspring.domain.City;
import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.domain.enums.CustomerType;
import br.pro.brand.cursojavaspring.dto.CustomerDTO;
import br.pro.brand.cursojavaspring.dto.CustomerInsertDTO;
import br.pro.brand.cursojavaspring.exceptions.DataIntegrityException;
import br.pro.brand.cursojavaspring.exceptions.ObjectNotFoundException;
import br.pro.brand.cursojavaspring.repositories.AdressRepository;
import br.pro.brand.cursojavaspring.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository rep;

    @Autowired
	private AdressRepository adressRepository;

    public Customer search(Integer id) {
        Optional<Customer> obj = rep.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Object not found! Id: " + id + 
            " ,Type: " + Customer.class.getName(), null)
        );
    }

    @Transactional
	public Customer insert(Customer obj) {
		obj.setId(null);
		obj = rep.save(obj);
		adressRepository.saveAll(obj.getAdresses());
		return obj;
	}
    
    public Customer update(Customer obj) {
		Customer newObj = search(obj.getId());
		updateData(newObj, obj);
		return rep.save(newObj);
	}

	public void delete(Integer id) {
		search(id);
		try {
			rep.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Its not possible to delete a customer that contains data in other entities");
		}
	}

	public List<Customer> findAll() {
		return rep.findAll();
	}

	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return rep.findAll(pageRequest);
	}

	public Customer fromDTO(CustomerDTO objDto) {
		return new Customer(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

    public Customer fromDTO(CustomerInsertDTO objDto) {
		Customer cli = new Customer(null, objDto.getName(), objDto.getEmail(), objDto.getIdentification(), CustomerType.valueOf(objDto.getType()));
		City cid = new City(objDto.getCityId(), null, null);
		Adress end = new Adress(null, objDto.getStreet(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getZipcode(), cli, cid);
		cli.getAdresses().add(end);
		cli.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2()!=null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3()!=null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}

	private void updateData(Customer newObj, Customer obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
    
}
