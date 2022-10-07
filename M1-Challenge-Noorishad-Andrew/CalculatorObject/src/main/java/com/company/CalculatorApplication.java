package com.company;

public class CalculatorApplication {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

//        Calculations
        System.out.println("1+1=" + calc.add(1, 1));
        System.out.println("23-52=" + calc.subtract(23,52));
        System.out.println("34*2=" + calc.multiply(34,2));
        System.out.println("12/3=" + calc.divide(12,3));
        System.out.println("12/7=" + calc.divide(12,7));
        System.out.println("3.4+2.3=" + calc.add(3.4,2.3));
        System.out.println("6.7*4.4=" + calc.multiply(6.7,4.4));
        System.out.println("5.5-0.5=" + calc.subtract(5.5,0.5));
        System.out.println("10.8/2.2=" + calc.divide(10.8,2.2));
    }
}
