package com.company.M2ChallengeNoorishadAndrew.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Month {


    @NotEmpty(message = "You must supply a value for number")
    @Size(min = 1, max = 12, message = "Number must be between 1 and 12")
    private Integer number;

    @NotEmpty(message = "You must supply a value for name")
    private String name;

    public Month() {}

    public Month(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return Objects.equals(number, month.number) && Objects.equals(name, month.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }
}
