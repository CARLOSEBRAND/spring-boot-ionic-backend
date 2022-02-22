package br.pro.brand.cursojavaspring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.domain.enums.CustomerType;
import br.pro.brand.cursojavaspring.dto.CustomerInsertDTO;
import br.pro.brand.cursojavaspring.repositories.CustomerRepository;
import br.pro.brand.cursojavaspring.resources.exception.FieldMessage;
import br.pro.brand.cursojavaspring.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerInsertDTO> {

    @Autowired
	private CustomerRepository rep;

    @Override
    public void initialize(CustomerInsert ann) {
    }

    @Override
    public boolean isValid(CustomerInsertDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType() != null) {
            if (objDto.getType().equals(CustomerType.FISICA.name()) && !BR.isValidCPF(objDto.getIdentification())) {
                list.add(new FieldMessage("identification", "Invalid CPF"));
            }

            if (objDto.getType().equals(CustomerType.JURIDICA.name())
                    && !BR.isValidCNPJ(objDto.getIdentification())) {
                list.add(new FieldMessage("identification", "Invalid CNPJ"));
            }
        }

        if (objDto.getEmail() != null) {
            Customer aux = rep.findByEmail(objDto.getEmail());
		    if (aux != null) {
	    		list.add(new FieldMessage("email", "Email already existing in database"));
	    	}
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
