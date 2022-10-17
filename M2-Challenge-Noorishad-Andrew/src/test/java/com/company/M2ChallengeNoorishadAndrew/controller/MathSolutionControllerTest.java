package com.company.M2ChallengeNoorishadAndrew.controller;

import com.company.M2ChallengeNoorishadAndrew.models.MathSolution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MathSolutionController.class)
public class MathSolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private MathSolution inputOperands;
    private Map<String, String> inputMap;

    private MathSolution missingOperand1;
    private MathSolution missingOperand2;

    private String inputJson;
    private String missingOperand1Json;
    private String missingOperand2Json;

    private MathSolution outputMathSolution;

    @Before
    public void setUp() throws Exception {
//        For standard tests
        inputOperands = new MathSolution();
        inputOperands.setOperand1(16);
        inputOperands.setOperand2(4);

        inputJson = mapper.writeValueAsString(inputOperands);

        outputMathSolution = new MathSolution();
        outputMathSolution.setOperand1(16);
        outputMathSolution.setOperand2(4);

//        For testing error if not both numbers
        inputMap = new HashMap<>();
        inputMap.put("operand1","16");
        inputMap.put("operand2","four");


//        For testing error if missing operands
        missingOperand1 = new MathSolution();
        missingOperand1.setOperand2(100);
        missingOperand2 = new MathSolution();
        missingOperand2.setOperand1(100);

        missingOperand1Json = mapper.writeValueAsString(missingOperand1);
        missingOperand2Json = mapper.writeValueAsString(missingOperand2);

    }

    @Test
    public void shouldReturnSumOfOperand1AndOperand2() throws Exception {

        outputMathSolution.setOperation("add");
        outputMathSolution.setAnswer(20);

        String outputJson = mapper.writeValueAsString(outputMathSolution);


        mockMvc.perform(
                        post("/add")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnSubtractionOfOperand1AndOperand2() throws Exception {

        outputMathSolution.setOperation("subtract");
        outputMathSolution.setAnswer(12);

        String outputJson = mapper.writeValueAsString(outputMathSolution);


        mockMvc.perform(
                        post("/subtract")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnMultiplicationOfOperand1AndOperand2() throws Exception {

        outputMathSolution.setOperation("multiply");
        outputMathSolution.setAnswer(64);

        String outputJson = mapper.writeValueAsString(outputMathSolution);


        mockMvc.perform(
                        post("/multiply")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnDivisionOfOperand1AndOperand2() throws Exception {

        outputMathSolution.setOperation("divide");
        outputMathSolution.setAnswer(4);

        String outputJson = mapper.writeValueAsString(outputMathSolution);


        mockMvc.perform(
                        post("/divide")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422StatusCodeWhenBothOperand1AndOperand2AreNotNumbersInAdd() throws Exception {

        inputMap.put("operation","add");
        inputMap.put("answer","20");

        String inputMapJson = mapper.writeValueAsString(inputMap);
        mockMvc.perform(
                        post("/add")
                                .content(inputMapJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenBothOperand1AndOperand2AreNotNumbersInSubtract() throws Exception {

        inputMap.put("operation","subtract");
        inputMap.put("answer","12");

        String inputMapJson = mapper.writeValueAsString(inputMap);
        mockMvc.perform(
                        post("/subtract")
                                .content(inputMapJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenBothOperand1AndOperand2AreNotNumbersInMultiply() throws Exception {

        inputMap.put("operation","multiply");
        inputMap.put("answer","64");

        String inputMapJson = mapper.writeValueAsString(inputMap);
        mockMvc.perform(
                        post("/subtract")
                                .content(inputMapJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenBothOperand1AndOperand2AreNotNumbersInDivide() throws Exception {

        inputMap.put("operation","divide");
        inputMap.put("answer","4");

        String inputMapJson = mapper.writeValueAsString(inputMap);
        mockMvc.perform(
                        post("/subtract")
                                .content(inputMapJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenMissingOperand1OrOperand2InAdd() throws Exception {

        mockMvc.perform(
                        post("/add")
                                .content(missingOperand1Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                        post("/add")
                                .content(missingOperand2Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenMissingOperand1OrOperand2InSubtract() throws Exception {

        mockMvc.perform(
                        post("/subtract")
                                .content(missingOperand1Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                        post("/subtract")
                                .content(missingOperand2Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenMissingOperand1OrOperand2InMultiply() throws Exception {

        mockMvc.perform(
                        post("/multiply")
                                .content(missingOperand1Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                        post("/multiply")
                                .content(missingOperand2Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenMissingOperand1OrOperand2InDivide() throws Exception {

        mockMvc.perform(
                        post("/divide")
                                .content(missingOperand1Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                        post("/divide")
                                .content(missingOperand2Json)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422StatusCodeWhenDividingByZero() throws Exception {
        MathSolution operand2Zero = new MathSolution();
        operand2Zero.setOperand1(2);
        operand2Zero.setOperand2(0);

        String operand2ZeroJson = mapper.writeValueAsString(operand2Zero);

        mockMvc.perform(
                        post("/divide")
                                .content(operand2ZeroJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}