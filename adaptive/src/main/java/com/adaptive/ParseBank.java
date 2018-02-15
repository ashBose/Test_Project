package com;

import java.io.*;
import java.util.*;
/*
INPUT:

N O V   2 3 R D
MTG: $1,613,729* from Avidia Bank
ADD: 169 Mountain Rd., Princeton
BORROWER: Diane L. Boudreau

MTG: $1,492,500 from Salem Five Cents
Savings Bank
ADD: Carlson Way and 148 Main St., Rutland
BORROWER: Gengel C&S Builders Inc., man-
aged by Leonard F. Gengel

N O V   2 4 T H
MTG: $4,000* from Avidia Bank
ADD: 169 Mountain Rd., Princeton
BORROWER: Diane L. Boudreau

OUTPUT
ADD|BORROWER|MTG|DATE
169 Mountain Rd., Princeton|Diane L. Boudreau|$1,613,729* from Avidia Bank|N O V   2 3 R D
Carlson Way and 148 Main St., Rutland|Gengel C&S Builders Inc., man- aged by Leonard F. Gengel|$1,492,500 from Salem Five Cents Savings Bank|N O V   2 3 R D
169 Mountain Rd., Princeton|Diane L. Boudreau|$4,000* from Avidia Bank|N O V 2 4 T H


-----------INPUT
N O V   2 3 R D
MTG: $1,613,729* from Avidia Bank
ADD: 169 Mountain Rd., Princeton
BORROWER: Diane L. Boudreau
NOTES: *Commercial mortgage for residential
property

MTG: $1,492,500 from Salem Five Cents
Savings Bank
ADD: Carlson Way and 148 Main St., Rutland
BORROWER: Gengel C&S Builders Inc., man-
aged by Leonard F. Gengel

N O V   2 1 S T
MTG: $56,180,000 from Cornerstone
Enhanced Mortgage Fund I REIT LLC
ADD: 177 Huntington Ave., Boston
BORROWER: BCSP VI Huntington Property
LLC, managed by Beacon Capital Partners

MTG: $5,400,000 from Rockland Trust Co.
ADD: 129-151 North St., Newton
BORROWER: Albemarle Realty Corp., man-
aged by Pasquale Franchi, president

MTG: $1,600,000 from The Village Bank
ADD: 95-97 Elm St., 72 Floral St., and 1185
Walnut St., Newton
BORROWER: Curtis P. O'Hara, John M.
O'Hara, and Karl J. O'Hara

MTG: $1,300,000 from Century Bank and
Trust Co.
ADD: 20-25 Industrial Rd., Walpole
BORROWER: James P. Di Silva and Thomas A.
Di Silva, trustees of The Webster TR

MTG: $1,120,000 from Bank Rhode Island
ADD: 35 Commercial Dr., Wrentham
BORROWER: Blackstone Valley Place
Associates, managed by David B. MacDonald

N O V   2 0 T H
BUYER: Ken's Food Inc., managed by Frank A.
Crowley III
SELLER: 325 Turnpike Road LLC, managed by
Cornerstone Real Estate Advisers
PRICE/ADD: $15,000,000; 325 Turnpike Rd.,
Southborough
NOTES: Single-story industrial property, constructed
1971; building size is 341,025 sf, lot size is 51.5
acres; last sold for $23,025,838.71 in March 2011

MTG: $1,050,000 and $3,200,000 from
Monson Savings Bank
ADD: 75 and 95 Post Office Park, Wilbraham
BORROWER: 75 Post Office Park LLC, man-
aged by Glen J. Garvey

--------OUTPUT
ADD|BORROWER|BUYER|MTG|NOTES|PRICE/ADD|SELLER|DATE
169 Mountain Rd., Princeton|Diane L. Boudreau||$1,613,729* from Avidia Bank|*Commercial mortgage for residential property|||N O V   2 3 R D
Carlson Way and 148 Main St., Rutland|Gengel C&S Builders Inc., man- aged by Leonard F. Gengel||$1,492,500 from Salem Five Cents Savings Bank||||N O V   2 3 R D
177 Huntington Ave., Boston|BCSP VI Huntington Property LLC, managed by Beacon Capital Partners||$56,180,000 from Cornerstone Enhanced Mortgage Fund I REIT LLC||||N O V   2 1 S T
129-151 North St., Newton|Albemarle Realty Corp., man- aged by Pasquale Franchi, president||$5,400,000 from Rockland Trust Co.||||N O V   2 1 S T
95-97 Elm St., 72 Floral St., and 1185 Walnut St., Newton|Curtis P. O'Hara, John M. O'Hara, and Karl J. O'Hara||$1,600,000 from The Village Bank||||N O V   2 1 S T
20-25 Industrial Rd., Walpole|James P. Di Silva and Thomas A. Di Silva, trustees of The Webster TR||$1,300,000 from Century Bank and Trust Co.||||N O V   2 1 S T
35 Commercial Dr., Wrentham|Blackstone Valley Place Associates, managed by David B. MacDonald||$1,120,000 from Bank Rhode Island||||N O V   2 1 S T
||Ken's Food Inc., managed by Frank A. Crowley III||Single-story industrial property, constructed 1971; building size is 341,025 sf, lot size is 51.5 acres; last sold for $23,025,838.71 in March 2011|$15,000,000; 325 Turnpike Rd., Southborough|325 Turnpike Road LLC, managed by Cornerstone Real Estate Advisers|N O V   2 0 T H
75 and 95 Post Office Park, Wilbraham|75 Post Office Park LLC, man- aged by Glen J. Garvey||$1,050,000 and $3,200,000 from Monson Savings Bank||||N O V 2 0 T H

 */
public class ParseBank {

    HashMap<String, HashSet<TreeMap<String, String>>> store;
    List<String> content = new ArrayList<String>();
    String oldDate;
    Set<String> dates = new HashSet<String>() {{
        add("J A N");
        add("F E B");
        add("M A R");
        add("A P R");
        add("M A Y");
        add("J U N");
        add("J U L");
        add("A U G");
        add("S E P");
        add("O C T");
        add("N O V");
        add("D E C");
    }};
    TreeSet<String> headers = new TreeSet<String>();

    bank() {
       store  = new HashMap<String, HashSet<TreeMap<String, String>>>();
    }

    public void display() {
        for(String hd: headers) {
            System.out.print(hd+"|");
        }
        System.out.println("DATE");
        StringBuilder result = new StringBuilder();
        for(String keyDate: store.keySet()) {
            for(TreeMap<String, String> subRecord: store.get(keyDate)) {
                StringBuilder sb = new StringBuilder();
                for(String key: headers) {
                    if (subRecord.get(key) == null) {
                        sb.append("|");
                    } else {
                        sb.append(subRecord.get(key));
                        sb.append("|");
                    }
                }
                sb.append(keyDate);
                result.append(sb.toString()+"\n");
            }
        }
        System.out.println(result.toString());
    }


    public void parse() {

        int start = 0;
        if (content.get(0).length() == 0)
            start += 1;
        while(start < content.size() ) {
            String key = null;
            String value = null;
            TreeMap<String, String> tmp = new TreeMap<String, String>();
            while(start < content.size() && content.get(start).length() != 0 ) {
                String tmpLine = content.get(start);
                if(tmpLine.contains(":"))  {
                    int index = tmpLine.indexOf(':');
                    key = tmpLine.substring(0, index);
                    value = tmpLine.substring(index+1);
                    tmp.put(key, value);
                    headers.add(key);
                }
                else if (tmpLine.length() > 5 && dates.contains(tmpLine.substring(0,5))) {
                    oldDate = tmpLine;
                    store.put(oldDate, new HashSet<TreeMap<String, String>>());
                    start ++;
                    continue;
                }
                else {
                 value += " " + tmpLine;
                 tmp.put(key, value);
                 start++;
                 continue;
                }
                start++;
            }
            HashSet<TreeMap<String, String>> subRecord = store.get(oldDate);
            subRecord.add(tmp);
            store.put(oldDate, subRecord);
            start ++;
        }

        //System.out.println(store);
    }

    public void filehandler() throws IOException {
        // The name of the file to open.
        String fileName = "test_cases_e5ol843a99a/input001.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                    content.add(line.trim());
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
       ParseBank bk = new ParseBank();
       try {
           bk.filehandler();
           bk.parse();
           bk.display();
       } catch(Exception e) {
       }
    }

}
