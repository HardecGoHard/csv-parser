package com.laba18.csvparser.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity<Long> {

    private Long customerId;

    private Integer trDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mcc_code_id")
    private MccCode mccCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tr_type_id")
    private TransactionType trType;

    private Double amount;

    private String terminalId;

    public Transaction() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getTrDay() {
        return trDay;
    }

    public void setTrDay(Integer trDay) {
        this.trDay = trDay;
    }

    public MccCode getMccCode() {
        return mccCode;
    }

    public void setMccCode(MccCode mccCode) {
        this.mccCode = mccCode;
    }

    public TransactionType getTrType() {
        return trType;
    }

    public void setTrType(TransactionType trType) {
        this.trType = trType;
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
        Transaction that = (Transaction) o;
        return Objects.equals(customerId, that.customerId) &&
                Objects.equals(trDay, that.trDay) &&
                Objects.equals(mccCode, that.mccCode) &&
                Objects.equals(trType, that.trType) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(terminalId, that.terminalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, trDay, mccCode, trType, amount, terminalId);
    }
}
