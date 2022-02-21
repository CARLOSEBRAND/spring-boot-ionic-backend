package br.pro.brand.cursojavaspring.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.pro.brand.cursojavaspring.services.validation.CustomerInsert;

@CustomerInsert
public class CustomerInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="The field can not be blank value")
	@Size(min=5, max=120, message="The category '${validatedValue}' must be between {min} and {max} characters long")
    private String name;
    
    @NotEmpty(message="The field can not be blank value")
	@Email(message="Invalid Mail")
    private String email;
    
    @NotEmpty(message="The field can not be blank value")
    private String identification;

    private String type;

	@NotEmpty(message="The field can not be blank value")
    private String street;
    
    @NotEmpty(message="The field can not be blank value")
    private String number;
    
    private String complement;
    private String district;
    
    @NotEmpty(message="The field can not be blank value")
    private String zipcode;

	@NotEmpty(message="The field can not be blank value")
    private String phone1;
	
    private String phone2;
	private String phone3;

	private Integer cityId;

	public CustomerInsertDTO() {
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return this.phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return this.phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    
}
