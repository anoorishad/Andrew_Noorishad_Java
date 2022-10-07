package com.company.pointofsale;

public class IceCream {
    public String name;
    public double price;

    public double taxRate;

    public IceCream(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

//    Custom Methods

    public double getPriceWithTax(double taxRate){
        return price + (price * taxRate);
    }

    public double applyHalfOffDiscount(){
        return price/2;
    }

    public String getThankYouMessage(){
        return "Thank you for your purchase of " + name + "!";
    }


}
