package com.company.factory;

public class IceCream {
    public String name;
    public String flavor;
    public String topping;

    public int flavInventory;
    public int topInventory;

    public IceCream(String name, String flavor, String topping, int flavInventory, int topInventory) {
        this.name = name;
        this.flavor = flavor;
        this.topping = topping;
        this.flavInventory = flavInventory;
        this.topInventory = topInventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public int getFlavInventory() {
        return flavInventory;
    }

    public void setFlavInventory(int flavInventory) {
        this.flavInventory = flavInventory;
    }

    public int getTopInventory() {
        return topInventory;
    }

    public void setTopInventory(int topInventory) {
        this.topInventory = topInventory;
    }


//    Custom Methods
    public int subtractIntFromFlavInventory(int flavReduce) {
        return flavInventory - flavReduce;
    }

    public int subtractIntFromTopInventory(int topReduce) {
        return topInventory - topReduce;
    }

    public int addIntToFlavInventory(int flavAddition) {
        return flavInventory + flavAddition;
    }

    public int addIntToTopInventory(int topAddition) {
        return topInventory + topAddition;
    }


}
