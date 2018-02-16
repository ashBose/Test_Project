package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ds {


    public static  HashMap<Integer, List<String>> noOfHits(String[] input) {

        HashMap<Integer, List<String>> result = new HashMap<Integer, List<String>>();
        HashMap<String, Integer> result2 = new HashMap<String, Integer>();


        for (String temp : input) {
            String[] domains = temp.split(",");
            int count = Integer.valueOf(domains[0]);
            String myDomain = domains[1];
            if (!result2.containsKey(myDomain)) {
                result2.put(myDomain, count);
            }
            String[] arr = myDomain.split("\\.");
            for (String tmpArr : arr) {
                //System.out.println(tmpArr);

                if (!result2.containsKey(tmpArr)) {
                    result2.put(tmpArr, count);
                } else {

                    int c = result2.get(tmpArr);
                    System.out.println(c + "  " + tmpArr);
                    result2.put(tmpArr, c + count);
                }

            }
        }

            for (String key : result2.keySet()) {
                int val = result2.get(key);
                if (!result.containsKey(val)) {
                    List<String> tmp = new ArrayList<String>();
                    tmp.add(key);
                    result.put(val, tmp);
                } else {
                    List<String> tmp = result.get(val);
                    tmp.add(key);
                    result.put(val, tmp);
                }
            }


            return result;
    }


    public static void main(String[] args) {

        String[] counts = {
                "900,google.com",
                "60,mail.yahoo.com",
                "10,mobile.sports.yahoo.com",
                "40,sports.yahoo.com",
                "300,yahoo.com",
                "10,stackoverflow.com",
                "2,en.wikipedia.org",
                "1,es.wikipedia.org" };

        System.out.println(noOfHits(counts));
    }


}
