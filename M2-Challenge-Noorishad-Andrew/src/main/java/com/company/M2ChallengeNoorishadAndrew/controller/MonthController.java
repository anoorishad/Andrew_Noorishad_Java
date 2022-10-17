package com.company.M2ChallengeNoorishadAndrew.controller;

import com.company.M2ChallengeNoorishadAndrew.models.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class MonthController {

    private static List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1,"January"),
            new Month(2, "February"),
            new Month(3, "March"),
            new Month(4,"April"),
            new Month(5,"May"),
            new Month(6, "June"),
            new Month(7,"July"),
            new Month(8,"August"),
            new Month(9,"September"),
            new Month(10,"October"),
            new Month(11,"November"),
            new Month(12, "December")
    ));

    @RequestMapping(value = "/month/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getMonthByNumber(@PathVariable Integer monthNumber) {
        Month foundMonth = null;

        for(Month month : monthList) {
            if(month.getNumber() == monthNumber) {
                foundMonth = month;
                break;
            }
        }

        if (foundMonth == null) {
            throw new IllegalArgumentException("Month number is not in range");
        }

        return foundMonth;
    }

    @RequestMapping(value = "/randomMonth", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getRandomMonth() {
        Month foundMonth = null;

//        Use random number generator to generate random number between 1 and 12
        Random myRandomNumberGenerator = new Random();
        int randomMonthInt = 1 + myRandomNumberGenerator.nextInt(12);

        for(Month month : monthList) {
            if (month.getNumber() == randomMonthInt) {
                foundMonth = month;
                break;
            }
        }
        return foundMonth;
    }
}
