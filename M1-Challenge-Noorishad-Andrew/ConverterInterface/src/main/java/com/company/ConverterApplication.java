package com.company;


public class ConverterApplication  {
    public static void main(String[] args) {
        ConverterIf ci = new ConverterIf();
        ConverterSwitch cs = new ConverterSwitch();

        System.out.println(ci.convertMonth(1));
        System.out.println(ci.convertDay(1));

        System.out.println(cs.convertMonth(12));
        System.out.println(cs.convertDay(7));

    }

}
