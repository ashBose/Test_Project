package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/*
You are in charge of a display advertising program. Your ads are displayed on websites all over the internet. You have some CSV input data that counts how many times you showed an ad on each individual domain. Every line consists of a count and a domain name. It looks like this:

counts = [ "900,google.com",
     "60,mail.yahoo.com",
     "10,mobile.sports.yahoo.com",
     "40,sports.yahoo.com",
     "300,yahoo.com",
     "10,stackoverflow.com",
     "2,en.wikipedia.org",
     "1,es.wikipedia.org" ]

Write a function that takes this input as a parameter and returns a data structure containing the number of hits that were recorded on each domain AND each domain under it. For example, an impression on "sports.yahoo.com" counts for "sports.yahoo.com", "yahoo.com", and "com". (Subdomains are added to the left of their parent domain. So "sports" and "sports.yahoo" are not valid domains.)

Expected output (in any order):
1320    com
 900    google.com
 410    yahoo.com
  60    mail.yahoo.com
  10    mobile.sports.yahoo.com
  50    sports.yahoo.com
  10    stackoverflow.com
   3    org
   3    wikipedia.org
   2    en.wikipedia.org
   1    es.wikipedia.org


 */


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
