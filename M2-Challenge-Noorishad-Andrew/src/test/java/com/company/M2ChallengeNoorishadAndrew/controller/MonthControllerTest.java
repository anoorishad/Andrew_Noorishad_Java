package com.company.M2ChallengeNoorishadAndrew.controller;

import com.company.M2ChallengeNoorishadAndrew.models.Month;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MonthController.class)
public class MonthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();




    @Test
    public void shouldReturnCorrectMonthNameAndNumberByNumber() throws Exception {
        Month outputMonth = new Month();
        outputMonth.setNumber(1);
        outputMonth.setName("January");

        String outputJson = mapper.writeValueAsString(outputMonth);


        mockMvc.perform(get("/month/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }

    @Test
    public void shouldReturn422StatusCodeIfRequestIsInvalid() throws Exception {

        mockMvc.perform(get("/month/13"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());



        mockMvc.perform(get("/month/four"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


    }

    @Test
    public void shouldReturnRandomMonthAndStatusCode200() throws Exception {

        mockMvc.perform(get("/randomMonth"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}