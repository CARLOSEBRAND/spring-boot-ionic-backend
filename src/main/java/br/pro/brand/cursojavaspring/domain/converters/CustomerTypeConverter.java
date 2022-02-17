package br.pro.brand.cursojavaspring.domain.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.pro.brand.cursojavaspring.domain.enums.CustomerType;

@Converter(autoApply = true)
public class CustomerTypeConverter implements AttributeConverter<CustomerType, String> {
 
    @Override
    public String convertToDatabaseColumn(CustomerType customerType) {
        if (customerType == null) {
            return null;
        }
        return customerType.getCode();
    }

    @Override
    public CustomerType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(CustomerType.values())
          .filter(c -> c.getCode().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }    
}
