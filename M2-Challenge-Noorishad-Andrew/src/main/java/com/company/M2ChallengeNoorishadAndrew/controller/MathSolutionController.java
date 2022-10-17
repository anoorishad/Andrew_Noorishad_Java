package com.company.M2ChallengeNoorishadAndrew.controller;

import com.company.M2ChallengeNoorishadAndrew.models.MathSolution;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MathSolutionController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution add(@RequestBody MathSolution mathSolution) {

        if(mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("add");
            mathSolution.setAnswer(mathSolution.getOperand1() + mathSolution.getOperand2());

        }else {throw new IllegalArgumentException("Invalid or missing operand1 and/or operand2");}

        return mathSolution;
    }

    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution subtract(@RequestBody MathSolution mathSolution) {

        if(mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("subtract");
            mathSolution.setAnswer(mathSolution.getOperand1() - mathSolution.getOperand2());

        }else {throw new IllegalArgumentException("Invalid or missing operand1 and/or operand2");}

        return mathSolution;
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution multiply(@RequestBody MathSolution mathSolution) {

        if(mathSolution.getOperand1() != null && mathSolution.getOperand2() != null) {
            mathSolution.setOperation("multiply");
            mathSolution.setAnswer(mathSolution.getOperand1() * mathSolution.getOperand2());

        }else {throw new IllegalArgumentException("Invalid or missing operand1 and/or operand2");}

        return mathSolution;
    }

    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution divide(@RequestBody MathSolution mathSolution) {

        if(mathSolution.getOperand1() != null && mathSolution.getOperand2() != null && mathSolution.getOperand2() != 0) {
            mathSolution.setOperation("divide");
            mathSolution.setAnswer(mathSolution.getOperand1() / mathSolution.getOperand2());

        }else {throw new IllegalArgumentException("Invalid or missing operand1 and/or operand2");}

        return mathSolution;
    }
}
