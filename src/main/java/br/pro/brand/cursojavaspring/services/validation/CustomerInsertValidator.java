package br.pro.brand.cursojavaspring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.pro.brand.cursojavaspring.domain.enums.CustomerType;
import br.pro.brand.cursojavaspring.dto.CustomerInsertDTO;
import br.pro.brand.cursojavaspring.resources.exception.FieldMessage;
import br.pro.brand.cursojavaspring.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerInsertDTO> {

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

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
