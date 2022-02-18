package br.pro.brand.cursojavaspring.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.pro.brand.cursojavaspring.domain.enums.PaymentStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
	private Integer id;
	private PaymentStatus paymentStatus;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "purchase_order_id")
    @MapsId
    private PurchaseOrder purchaseOrder;


    public Payment() {
    }

    public Payment(Integer id, PaymentStatus paymentStatus, PurchaseOrder purchaseOrder) {
        this.id = id;
        this.paymentStatus = paymentStatus;
        this.purchaseOrder = purchaseOrder;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PurchaseOrder getPurchaseOrder() {
        return this.purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Payment)) {
            return false;
        }
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
}
