package com.adaptive;

public class Program1 {


    /*
    The method will take a string and reverse the words in the string.
   It is mentioned in the question that words are seperated by a blank space, so
   it was split based on space.

   Assumption: The Size of the input string is < Integer.MAX_VALUE
     */
    public  static String reverseWord(final String input) {
        if( input ==null || input.length()==0)
            return input;

        String[] arr = input.split(" ");
        StringBuilder sb= new StringBuilder();

        for(int i=arr.length-1;i>=0;i--)
            if(!arr[i].equals(""))
                sb.append(arr[i]).append(" ");

        return sb.length()==0?"":sb.substring(0,sb.length()-1);
    }

}