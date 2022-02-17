package br.pro.brand.cursojavaspring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.pro.brand.cursojavaspring.domain.enums.CustomerType;

@Entity
public class Customer implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
    private String email;
    private String identification;
    private CustomerType type;

    //cliente tem muitos endereços
    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Adress> adresses = new ArrayList<>();

    //cliente tem muitos telefones
    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();


    public Customer() {
    }

    public Customer(Integer id, String name, String email, String identification, CustomerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.identification = identification;
        this.type = type;        
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentification() {
        return this.identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public CustomerType getType() {
        return this.type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public List<Adress> getAdresses() {
        return this.adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public Set<String> getPhones() {
        return this.phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }    

}
