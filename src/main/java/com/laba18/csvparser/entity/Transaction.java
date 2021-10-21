package com.laba18.csvparser.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;

@Data
public class Transaction {
    @Id
    private Long transaction_id;
    private Long customer_id;
    private String tr_datetime;
    private Long mcc_code;
    private Long tr_type;
    private Long amount;
    private Long term_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return new EqualsBuilder()
                .append(customer_id, that.customer_id)
                .append(tr_datetime, that.tr_datetime)
                .append(mcc_code, that.mcc_code)
                .append(tr_type, that.tr_type)
                .append(amount, that.amount)
                .append(term_id, that.term_id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customer_id)
                .append(tr_datetime)
                .append(mcc_code)
                .append(tr_type)
                .append(amount)
                .append(term_id)
                .toHashCode();
    }
}
