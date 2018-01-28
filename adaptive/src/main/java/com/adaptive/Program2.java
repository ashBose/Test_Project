package com.adaptive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Write a method that takes a 7-digit telephone number and prints out all the possible combination of letters that can
represent the given number. Because 0 and 1 keys have no letters on them, you should change only the digits 2-9 to
letters.
You’ll be passed an array of 7 integers, with each element being one digit in the number. You may assume that only valid
phone numbers will be passed to the method.
You can use the helper routine
char getCharKey (int telephoneKey, int place)
Which take a telephone key (0-9) and a place of either 1, 2, 3 and returns the character corresponding to the letter in
that position on the specified key. For example,
getCharKey (3, 2) will return ‘E’ because
the telephone key 3 has the letters ‘DEF’ on it and ‘E’ is the second letter
Note: Telephone key 0 and 1 maps to nothing
2 -> ‘ABC’, 3 -> ‘DEF’, 4 -> ‘GHI’, 5 -> ‘JKL’, 6 -> ‘MNO’, 7 -> ‘PRS’, 8 -> ‘TUV’, 9 -> ‘WXY’
 */

//comment: In the input 'Q' and 'Z' is missing


public class Program2 {

    HashMap<Integer, char[]> charMap;
    /* As the mobile number combination is exponential complexity,
     we maintain a cache to store the result and make the process faster
     */
    HashMap<String, List<String>> cacheStore;

    Program2() {
     setup();
    }

    public void setup(){
        charMap = new HashMap<Integer, char[]>();
        charMap.put(2, new char[]{'a','b','c'});
        charMap.put(3, new char[]{'d','e','f'});
        charMap.put(4, new char[]{'g','h','i'});
        charMap.put(5, new char[]{'j','k','l'});
        charMap.put(6, new char[]{'m','n','o'});
        charMap.put(7, new char[]{'p','q','r','s'});
        charMap.put(8, new char[]{'t','u','v'});
        charMap.put(9, new char[]{'w','x','y','z'});
        cacheStore = new HashMap<String, List<String>>();
    }


    public Character getCharKey(int source, int position) {
        return charMap.get(source)[position];
    }


    public void  printWordMobile(String  inputNumber, int curr_digit, List<String> result, StringBuilder sb)
    {

        if (curr_digit == inputNumber.length())
        {
            result.add(sb.toString());
            return ;
        }
        int digit = Character.getNumericValue(inputNumber.charAt(curr_digit));
        for (int i=0; i< charMap.get(digit).length; i++)
        {
            sb.append(getCharKey(digit,i));
            printWordMobile(inputNumber, curr_digit+1, result, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    public List<String>  getCombinationation(String inputNumber) {
        List<String> result = new ArrayList<String>();

        if(inputNumber == null || inputNumber.length() == 0 ||
                inputNumber.indexOf('0') >= 0 ||
                inputNumber.indexOf('1') >= 0 )
            return result;
        StringBuilder sb = new StringBuilder();
        if(cacheStore.containsKey(inputNumber))
            return cacheStore.get(inputNumber);
        printWordMobile(inputNumber, 0,result, sb);
        cacheStore.put(inputNumber, result);
        return result;
    }


    public static void main(String args[]) {

        Program2 p2 = new Program2();
        System.out.println(p2.getCombinationation("23"));
    }
}
