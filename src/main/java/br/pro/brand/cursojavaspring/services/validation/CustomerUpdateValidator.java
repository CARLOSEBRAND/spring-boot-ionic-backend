package br.pro.brand.cursojavaspring.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.pro.brand.cursojavaspring.domain.Customer;
import br.pro.brand.cursojavaspring.dto.CustomerDTO;
import br.pro.brand.cursojavaspring.repositories.CustomerRepository;
import br.pro.brand.cursojavaspring.resources.exception.FieldMessage;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

    @Autowired
	private HttpServletRequest request;
    
    @Autowired
	private CustomerRepository rep;

    @Override
    public void initialize(CustomerUpdate ann) {
    }

    @Override
    public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getEmail() != null) {

            @SuppressWarnings("unchecked")
            Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            Integer uriId = Integer.parseInt(map.get("id"));

            Customer aux = rep.findByEmail(objDto.getEmail());
            if (aux != null && !aux.getId().equals(uriId)) {
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
