package br.pro.brand.cursojavaspring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.pro.brand.cursojavaspring.domain.Customer;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="The field can not be blank value")
	@Size(min=5, max=120, message="The category '${validatedValue}' must be between {min} and {max} characters long")
	private String name;

	@NotEmpty(message="The field can not be blank value")
	@Email(message="Invalid Mail")
	private String email;

	public CustomerDTO() {
	}

	public CustomerDTO(Customer obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
