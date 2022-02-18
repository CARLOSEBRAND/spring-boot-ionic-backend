package br.pro.brand.cursojavaspring.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.pro.brand.cursojavaspring.domain.enums.PaymentStatus;

@Entity
public class BankSlipPayment extends Payment {

    private static final long serialVersionUID = 1L;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date expireAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date paymentAt;

    public BankSlipPayment() {
    }

    public BankSlipPayment(Integer id, PaymentStatus paymentStatus, PurchaseOrder purchaseOrder, Date expireAt, Date paymentAt) {
        super(id, paymentStatus, purchaseOrder);
        this.expireAt = expireAt;
        this.paymentAt = paymentAt;
    }

    public Date getExpireAt() {
        return this.expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public Date getPaymentAt() {
        return this.paymentAt;
    }

    public void setPaymentAt(Date paymentAt) {
        this.paymentAt = paymentAt;
    }
 

}
