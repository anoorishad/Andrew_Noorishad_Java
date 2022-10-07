package com.company;

public class App {
    public static void main(String[] args) {

        Farmer dave = new Farmer("Dave");

        Constable ray = new Constable("Ray", "The World");

        Warrior tom = new Warrior("Tom");


//        Method usage
        System.out.println("Is Dave plowing? " + dave.isPlowing());

        System.out.println(ray.arrest(ray, dave));

        System.out.println(tom.attack(dave));


    }
}
