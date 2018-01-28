package com.adaptive;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestProgram1 {

    String input = "";
    String output ="";

    public void displaySuccess(String testInput) {
        System.out.println("Success with Test " + testInput);
    }

    @Test
    public void testP1() {
        assertEquals(Program1.reverseWord(input), output);

        input = "the sky is blue";
        output = "blue is sky the";
        assertEquals(Program1.reverseWord(input), output);
        displaySuccess(input);

        input="1  ";
        output="1";
        assertEquals(Program1.reverseWord(input), output);
        displaySuccess(input);

        input = null;
        output = null;
        assertEquals(Program1.reverseWord(input), output);
        displaySuccess(input);

        input="Do or do not, there is no try.";
        output = "try. no is there not, do or Do";
        assertEquals(Program1.reverseWord(input), output);
        displaySuccess(input);


        input="1  2    3     , 5";
        output="5 , 3 2 1";
        assertEquals(Program1.reverseWord(input), output);
        displaySuccess(input);


        input="    ";
        output="";
        assertEquals(Program1.reverseWord(input), output);
        displaySuccess(input);

    }
}
