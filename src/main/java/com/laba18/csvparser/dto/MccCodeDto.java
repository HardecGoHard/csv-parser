package com.laba18.csvparser.dto;

import com.opencsv.bean.CsvBindByName;

public class MccCodeDto {
    @CsvBindByName(column = "mcc_code")
    private Long mccCode;
    @CsvBindByName(column = "mcc_description")
    private String mccDescription;

    public MccCodeDto() {
    }

    public Long getMccCode() {
        return mccCode;
    }

    public void setMccCode(Long mccCode) {
        this.mccCode = mccCode;
    }

    public String getMccDescription() {
        return mccDescription;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    @Override
    public String toString() {
        return "MccCodeDto{" +
                "mccCode=" + mccCode +
                ", mccDescription='" + mccDescription + '\'' +
                '}';
    }
}
