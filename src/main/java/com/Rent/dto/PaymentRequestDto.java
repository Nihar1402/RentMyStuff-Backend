package com.Rent.dto;

public class PaymentRequestDto {


    private String amount;
    private String info;

    public PaymentRequestDto() {
    }

    public PaymentRequestDto(String amount, String info) {
        this.amount = amount;
        this.info = info;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
