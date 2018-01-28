package com.adaptive;


import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestProgram3 {

    static Program3 p3;
    String input = "";
    boolean output;

    @BeforeClass
    public static void setUp() {

        p3 = new Program3();
    }

    public void displaySuccess(String testInput) {
        System.out.println("Success with Test " + testInput);
    }

    @Test
    public void doTestParanthesis() {

       output = p3.isValid(input) ;
       assertEquals(output, true);
        displaySuccess(input);

       input = ")[](";
       output = p3.isValid(input) ;
        assertEquals(output, false);
        displaySuccess(input);

        input = null;
        output = p3.isValid(input) ;
        assertEquals(output, true);
        displaySuccess(input);

        input = "(()){}{}";
        output = p3.isValid(input) ;
        assertEquals(output, true);
        displaySuccess(input);


        input = "[(){()}]";
        output = p3.isValid(input) ;
        assertEquals(output, true);
        displaySuccess(input);

        input = "[][][}";
        output = p3.isValid(input) ;
        assertEquals(output, false);
        displaySuccess(input);


        input = "(";
        output = p3.isValid(input) ;
        assertEquals(output, false);
        displaySuccess(input);

        input = "}{";
        output = p3.isValid(input) ;
        assertEquals(output, false);
        displaySuccess(input);

        input = "(((()))";
        output = p3.isValid(input) ;
        assertEquals(output, false);
        displaySuccess(input);


        input = "{[}]";
        output = p3.isValid(input) ;
        assertEquals(output, false);
        displaySuccess(input);
    }



}
