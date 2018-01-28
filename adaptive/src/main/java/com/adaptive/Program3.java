package com.adaptive;

import java.util.Stack;

public class Program3 {

    public boolean isMatchingParethenthesis(char character1, char character2)
    {
        if(character1 == '(' && character2 == ')')
            return true;
        else if(character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }

    public  boolean isValid(String input) {

        if(input == null || input.length() == 0) {
            return true;
        }
        Stack<Character> st= new Stack<Character>();
        char[] exp = input.toCharArray();
        for(int i=0;i<exp.length;i++)
        {
            if(exp[i]=='(' || exp[i]=='{' || exp[i]=='[')
                st.push(exp[i]);
            if(exp[i]==')' || exp[i]=='}' || exp[i]==']')
            {
                if(st.isEmpty())
                    return false;
                else if(!isMatchingParethenthesis(st.pop(),exp[i]))
                    return false;
            }
        }
        if(st.isEmpty())
            return true;
        else
            return false;

    }
}

