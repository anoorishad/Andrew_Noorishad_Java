package com.company;

import java.util.Objects;

public class Constable extends Base{
    private String jurisdiction;

    public Constable(String name, String jurisdiction) {
        super(name, 60, 100, 60, 20, 5, false, false);
        this.jurisdiction = jurisdiction;
    }

    public String arrest(Constable character1, Base character2) {
        return character1.getName() + " arrested " + character2.getName() + ". Enjoy jail!";
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constable constable = (Constable) o;
        return Objects.equals(jurisdiction, constable.jurisdiction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jurisdiction);
    }
}
