package br.pro.brand.cursojavaspring.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderDetail implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderDetailPK id = new OrderDetailPK();
    
    private Double discount;
	private Integer quantity;
    private Double price;

    public OrderDetail() {
    }

    public OrderDetail(PurchaseOrder purchaseOrder, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setPurchaseOrder(purchaseOrder);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubTotal() {
		return (price - discount) * quantity;
	}

    @JsonIgnore
    public PurchaseOrder getPurchaseOrder() {
        return id.getPurchaseOrder();
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		id.setPurchaseOrder(purchaseOrder);
	}

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
		id.setProduct(product);
	}
    
    public OrderDetailPK getId() {
        return this.id;
    }

    public void setId(OrderDetailPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDetail)) {
            return false;
        }
        OrderDetail orderDetail = (OrderDetail) o;
        return Objects.equals(id, orderDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
}
