package com.laba18.csvparser.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "mcc_code")
public class MccCode {
    @Id
    private Long id;

    private String mccDescription;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public MccCode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMccDescription() {
        return mccDescription;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MccCode mccCode = (MccCode) o;
        return Objects.equals(id, mccCode.id) &&
                Objects.equals(mccDescription, mccCode.mccDescription) &&
                Objects.equals(transactions, mccCode.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mccDescription, transactions);
    }
}
