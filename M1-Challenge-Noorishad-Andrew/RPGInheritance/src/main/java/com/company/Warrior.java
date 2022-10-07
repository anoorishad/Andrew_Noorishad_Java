package com.company;

import java.util.Objects;

public class Warrior extends Base{

    private int shieldStrength;

    public Warrior(String name) {
        super(name, 75, 100, 100, 50, 10, false, false);
        shieldStrength = 100;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Warrior warrior = (Warrior) o;
        return shieldStrength == warrior.shieldStrength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shieldStrength);
    }
}
