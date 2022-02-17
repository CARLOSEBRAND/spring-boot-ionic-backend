package br.pro.brand.cursojavaspring.domain.converters;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.pro.brand.cursojavaspring.domain.enums.PaymentStatus;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {
 
    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus) {
        if (paymentStatus == null) {
            return null;
        }
        return paymentStatus.getCode();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(PaymentStatus.values())
          .filter(c -> c.getCode().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }    
}
