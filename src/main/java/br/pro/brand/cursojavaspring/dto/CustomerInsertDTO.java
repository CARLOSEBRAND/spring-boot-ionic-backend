package br.pro.brand.cursojavaspring.dto;

import java.io.Serializable;

public class CustomerInsertDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
    private String email;
    private String identification;
    private String type;

	private String street;
    private String number;
    private String complement;
    private String district;
    private String zipcode;

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
