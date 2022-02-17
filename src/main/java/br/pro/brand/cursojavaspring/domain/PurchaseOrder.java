package br.pro.brand.cursojavaspring.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PurchaseOrder implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date createdAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_adress_id")
    private Adress deliveryAdress;


    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, Date createdAt, Customer customer, Adress deliveryAdress) {
        this.id = id;
        this.createdAt = createdAt;
        this.customer = customer;
        this.deliveryAdress = deliveryAdress;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Adress getDeliveryAdress() {
        return this.deliveryAdress;
    }

    public void setDeliveryAdress(Adress deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder order = (PurchaseOrder) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
}
