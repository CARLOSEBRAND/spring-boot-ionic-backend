package br.pro.brand.cursojavaspring.domain.enums;

public enum PaymentStatus {

    PENDENTE("P"), QUITADO("Q"), CANCELADO("C");

    private String code;

    private PaymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
}
