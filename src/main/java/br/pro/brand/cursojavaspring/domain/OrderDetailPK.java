package br.pro.brand.cursojavaspring.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetailPK implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public PurchaseOrder getPurchaseOrder() {
        return this.purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDetailPK)) {
            return false;
        }
        OrderDetailPK orderDetailPK = (OrderDetailPK) o;
        return Objects.equals(purchaseOrder, orderDetailPK.purchaseOrder) && Objects.equals(product, orderDetailPK.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseOrder, product);
    }
    
}
