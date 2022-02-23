package br.pro.brand.cursojavaspring.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.pro.brand.cursojavaspring.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("creditCardPayment")
public class CreditCardPayment extends Payment {

    private static final long serialVersionUID = 1L;
    
    private Integer partNumber;

    public CreditCardPayment() {
    }

    public CreditCardPayment(Integer id, PaymentStatus payment_status, PurchaseOrder purchaseOrder, Integer partNumber) {
        super(id, payment_status, purchaseOrder);
        this.partNumber = partNumber;        
    }


    public Integer getPartNumber() {
        return this.partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

}
