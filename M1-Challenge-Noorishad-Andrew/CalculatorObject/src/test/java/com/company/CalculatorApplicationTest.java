package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorApplicationTest {

    Calculator calc;

    @Before
    public void setUp() {
        calc = new Calculator();
    }

//    Int Tests
    @Test
    public void shouldAddTwoInts() {
        assertEquals(5,calc.add(1,4));
        assertEquals(-10,calc.add(-20,10));
        assertEquals(100,calc.add(60,40));
    }

    @Test
    public void shouldSubtractTwoInts() {
        assertEquals(-3,calc.subtract(1,4));
        assertEquals(-30,calc.subtract(-20,10));
        assertEquals(20,calc.subtract(60,40));
    }

    @Test
    public void shouldMultiplyTwoInts() {
        assertEquals(4,calc.multiply(1,4));
        assertEquals(-200,calc.multiply(-20,10));
        assertEquals(24,calc.multiply(6,4));
    }

    @Test
    public void shouldDivideTwoInts() {
        assertEquals(4,calc.divide(16,4));
        assertEquals(20,calc.divide(100,5));
        assertEquals(6,calc.divide(24,4));
    }

//    Double Tests
    @Test
    public void shouldAddTwoDoubles() {
        assertEquals(6.0,calc.add(1.5,4.5),1);
        assertEquals(-10,calc.add(-19.5,9.5),1);
        assertEquals(100.8,calc.add(59.5,41.3),1);
    }

    @Test
    public void shouldSubtractTwoDoubles() {
        assertEquals(-3.3,calc.subtract(1.5,4.8),1);
        assertEquals(-31.3,calc.subtract(-20.7,10.6),1);
        assertEquals(20.4,calc.subtract(60.7,40.3),1);
    }

    @Test
    public void shouldMultiplyTwoDoubles() {
        assertEquals(7.31,calc.multiply(1.7,4.3),1);
        assertEquals(-206.04,calc.multiply(-20.4,10.1),1);
        assertEquals(28.81,calc.multiply(6.7,4.3),1);
    }

    @Test
    public void shouldDivideTwoDoubles() {
        assertEquals(3.95,calc.divide(16.6,4.2),1);
        assertEquals(18.65,calc.divide(100.7,5.4),1);
        assertEquals(5.19,calc.divide(24.4,4.7),1);
    }
}