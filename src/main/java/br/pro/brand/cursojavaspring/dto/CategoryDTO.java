package br.pro.brand.cursojavaspring.dto;

import java.io.Serializable;

import br.pro.brand.cursojavaspring.domain.Category;

public class CategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
    private Integer id;
	private String description;


    public CategoryDTO() {
    }

    public CategoryDTO(Category obj) {
        id = obj.getId();
        description = obj.getDescription();
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
