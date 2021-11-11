package com.laba18.csvparser.dto;

import com.opencsv.bean.CsvBindByName;

public class TransactionTypeDto {
    @CsvBindByName(column = "tr_type")
    private Long TypeCode;
    @CsvBindByName(column = "tr_description")
    private String description;

    public TransactionTypeDto() {
    }

    public Long getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(Long typeCode) {
        TypeCode = typeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionTypeDto{" +
                "TypeCode=" + TypeCode +
                ", description='" + description + '\'' +
                '}';
    }
}
