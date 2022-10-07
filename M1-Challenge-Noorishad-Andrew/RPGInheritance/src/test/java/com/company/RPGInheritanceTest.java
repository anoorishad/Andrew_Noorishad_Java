package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RPGInheritanceTest {

    Farmer dave;
    Constable ray;
    Warrior tom;

    @Before
    public void setUp() throws Exception {
        dave = new Farmer("Dave");
        ray = new Constable("Ray", "The World");
        tom = new Warrior("Tom");
    }

    @Test
    public void shouldOutputAttackMessage(){
        String expectedResult = "Tom attacked Dave with force!";
        String actualResult = tom.attack(dave);

        assertEquals(expectedResult,actualResult);
    }

    @Test
    public void shouldOutputArrestMessage(){
        String expectedResult = "Ray arrested Tom. Enjoy jail!";
        String actualResult = ray.arrest(ray, tom);

        assertEquals(expectedResult,actualResult);
    }
}