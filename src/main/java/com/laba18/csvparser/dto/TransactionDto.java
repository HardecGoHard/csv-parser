package com.laba18.csvparser.dto;


import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {
    @CsvBindByName(column = "customer_id")
    private Long customer_id;
    @CsvBindByName(column = "tr_datetime")
    private String tr_datetime;
    @CsvBindByName(column = "mcc_code")
    private Long mcc_code;
    @CsvBindByName(column = "tr_type")
    private Long tr_type;
    @CsvBindByName(column = "amount")
    private Long amount;
    @CsvBindByName(column = "term_id")
    private Long term_id;

    @Override
    public String toString() {
        return "TransactionDto{" +
                "customer_id=" + customer_id +
                ", tr_datetime='" + tr_datetime + '\'' +
                ", mcc_code=" + mcc_code +
                ", tr_type=" + tr_type +
                ", amount=" + amount +
                ", term_id=" + term_id +
                '}';
    }
}
