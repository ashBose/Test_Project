package com.adaptive;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.*;

public class TestProgram2 {

    public void displaySuccess(String testInput) {
        System.out.println("Success with Test " + testInput);
    }

    @Test
    public void phoneTest() {


        Program2 p2 = new Program2();
        String input = null;
        List<String> output = p2.getCombinationation(input);
        assertEquals(output.isEmpty(), true);

        input = "0";
        output = p2.getCombinationation(input);
        assertEquals(output.isEmpty(), true);

        input = "1";
        output = p2.getCombinationation(input);
        assertEquals(output.isEmpty(), true);


        input = "23";
        output = p2.getCombinationation(input);
        assertThat(output, contains("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));

    }
}
