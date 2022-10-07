package com.company;

import com.company.pointofsale.IceCream;

public class IceCreamApplication {
    public static void main(String[] args) {

//        Using POS methods and constructor
        IceCream posTest = new IceCream("Rocky Road", 2.99);

        System.out.println(posTest.getThankYouMessage());
        System.out.println(posTest.getPriceWithTax(.1));
        System.out.println(posTest.applyHalfOffDiscount());


//        Using factory methods and constructor
        com.company.factory.IceCream factTest = new com.company.factory.IceCream("Vanilla Brownie","Vanilla", "Brownie",100,100);

        System.out.println(factTest.flavInventory = factTest.addIntToFlavInventory(1));
        System.out.println(factTest.topInventory = factTest.addIntToTopInventory(1));

    }
}
