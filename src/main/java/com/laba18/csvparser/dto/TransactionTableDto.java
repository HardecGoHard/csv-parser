package com.laba18.csvparser.dto;

import com.opencsv.bean.CsvBindByName;

public class TransactionTableDto {
    @CsvBindByName(column = "tr_datetime")
    private String trDate;

    @CsvBindByName(column = "amount")
    private Double amount;

    @CsvBindByName(column = "mcc_code")
    private Long mccCode;

    public TransactionTableDto() {
    }

    public TransactionTableDto(String trDate, Double amount, Long mccCode) {
        this.trDate = trDate;
        this.amount = amount;
        this.mccCode = mccCode;
    }

    public Long getMccCode() {
        return mccCode;
    }

    public void setMccCode(Long mccCode) {
        this.mccCode = mccCode;
    }

    public String getTrDate() {
        return trDate;
    }

    public void setTrDate(String trDate) {
        this.trDate = trDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
