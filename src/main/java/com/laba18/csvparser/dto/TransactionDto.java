package com.laba18.csvparser.dto;


import com.opencsv.bean.CsvBindByName;

import java.util.Objects;


public class TransactionDto {
    @CsvBindByName(column = "customer_id")
    private Long customerId;
    @CsvBindByName(column = "tr_datetime")
    private String trDatetime;
    @CsvBindByName(column = "mcc_code")
    private Long mccCode;
    @CsvBindByName(column = "tr_type")
    private Long trTypeCode;
    @CsvBindByName(column = "amount")
    private Double amount;
    @CsvBindByName(column = "term_id")
    private String terminalId;


    public TransactionDto() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTrDatetime() {
        return trDatetime;
    }

    public void setTrDatetime(String trDatetime) {
        this.trDatetime = trDatetime;
    }

    public Long getMccCode() {
        return mccCode;
    }

    public void setMccCode(Long mccCode) {
        this.mccCode = mccCode;
    }

    public Long getTrTypeCode() {
        return trTypeCode;
    }

    public void setTrTypeCode(Long trTypeCode) {
        this.trTypeCode = trTypeCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(trDatetime, that.trDatetime) &&
                Objects.equals(mccCode, that.mccCode) &&
                Objects.equals(trTypeCode, that.trTypeCode) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(terminalId, that.terminalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, trDatetime, mccCode, trTypeCode, amount, terminalId);
    }
}
