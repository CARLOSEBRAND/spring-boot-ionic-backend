package br.pro.brand.cursojavaspring.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class State implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<City> cities = new ArrayList<>();


    public State() {
    }


    public State(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;        
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

    public List<City> getCities() {
        return this.cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof State)) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }






    
}
