package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterApplicationTest {

    ConverterIf ci;
    ConverterSwitch cs;

    @Before
    public void setUp() {
        ci = new ConverterIf();
        cs = new ConverterSwitch();
    }

//    ConverterIf Tests
    @Test
    public void shouldConvertMonthIntToStringIf(){
        String expectedResult = "January";
        String actualResult = ci.convertMonth(1);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldConvertDayIntToStringIf(){
        String expectedResult = "Sunday";
        String actualResult = ci.convertDay(1);
        assertEquals(expectedResult, actualResult);
    }


//    ConverterSwitch Tests
    @Test
    public void shouldConvertMonthIntToStringSwitch(){
        String expectedResult = "February";
        String actualResult = cs.convertMonth(2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldConvertDayIntToStringSwitch(){
        String expectedResult = "Monday";
        String actualResult = cs.convertDay(2);
        assertEquals(expectedResult, actualResult);
    }

}