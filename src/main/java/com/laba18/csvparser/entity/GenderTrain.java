package com.laba18.csvparser.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gender_trains")
public class GenderTrain {
    @Id
    private Long id;
    @Column
    private Integer gender;
    @Column
    private Long customerId;

    public GenderTrain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenderTrain that = (GenderTrain) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, customerId);
    }
}
