package com.example.handlingformsubmission;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
     * Test 1:
     * Verifies that the greeting form loads successfully.
     */
    @Test
    void testGreetingFormLoads() throws Exception {

        System.out.println("Running testGreetingFormLoads...");

        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attributeExists("greeting"));

        System.out.println("PASSED: Greeting form loaded correctly.");
    }


    /*
     * Test 2:
     * Verifies that a complete and valid form submission
     * returns the result page.
     */
    @Test
    void testValidGreetingSubmission() throws Exception {

        System.out.println("Running testValidGreetingSubmission...");

        mockMvc.perform(post("/greeting")
                        .param("id", "V12345678")
                        .param("month", "9")
                        .param("day", "11")
                        .param("year", "2026")
                        .param("expectedGraduationYear", "2027")
                        .param("content", "JUnit test message"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("greeting"));

        System.out.println("PASSED: Valid submission returned the result page.");
    }


    /*
     * Test 3:
     * Verifies that a blank Student ID is rejected.
     */
    @Test
    void testBlankStudentIdReturnsError() throws Exception {

        System.out.println("Running testBlankStudentIdReturnsError...");

        mockMvc.perform(post("/greeting")
                        .param("id", "")
                        .param("month", "9")
                        .param("day", "11")
                        .param("year", "2026")
                        .param("expectedGraduationYear", "2027")
                        .param("content", "JUnit test message"))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("error", "All fields are required."));

        System.out.println("PASSED: Blank Student ID was rejected.");
    }


    /*
     * Test 4:
     * Verifies that a blank message is rejected.
     */
    @Test
    void testBlankMessageReturnsError() throws Exception {

        System.out.println("Running testBlankMessageReturnsError...");

        mockMvc.perform(post("/greeting")
                        .param("id", "V12345678")
                        .param("month", "9")
                        .param("day", "11")
                        .param("year", "2026")
                        .param("expectedGraduationYear", "2027")
                        .param("content", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("greeting"))
                .andExpect(model().attribute("error", "All fields are required."));

        System.out.println("PASSED: Blank message was rejected.");
    }
}