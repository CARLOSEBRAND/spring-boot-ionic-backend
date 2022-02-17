package br.pro.brand.cursojavaspring.domain.enums;

public enum CustomerType {

    FISICA("PF"), JURIDICA("PJ");

    private String code;

    private CustomerType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
}
