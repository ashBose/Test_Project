
/*
HACKER RANK TEST

fileName,owner,docType,applicationId,contentLength
bank_statement_123,Tony Stark,bank_statement,1,1000
tax_document_1,Tony Stark,tax_return,1,16001
tax_document_2,Steve Rogers,tax_return,2,2000
document_423,Thor Odinson,tax_return,3,1500
medical_report_1,Thor Odinson,medical_history,3,15000
prescription_1,Stephen Strange,medical_history,4,200
steven_asset,Stephen Strange,bank_statement,4,4000

bank_statement
2 3
medical_history
1 2
tax_return
4

 */

import java.io.*;
import java.util.*;

public class solution {
    String fileName;
    boolean isHeaderRead;
    Map<String, Set<String>> LoanDocument;
    Set<String> applicationIDSet;

    solution(String fileName) {
        this.fileName = fileName;
        isHeaderRead = false;
        applicationIDSet = new TreeSet<>();
        LoanDocument = new TreeMap<>();
    }

    public void fileHandler() throws IOException {
        String line = null;
        String docType = null;
        String applicationID;
        try {
            FileReader fileReader =
                    new FileReader(fileName);

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if(!isHeaderRead) { //skip header
                    isHeaderRead = true;
                    continue;
                }
                //split it based on Comma
                String[] tmpParse = line.split(",");
                if (tmpParse.length != 5)
                    throw new IOException(
                            "Invalid Format " +
                                    fileName );

                docType = tmpParse[2];
                applicationID = tmpParse[3];
                applicationIDSet.add(applicationID);
                Set<String>tmpIDStore;
                if(LoanDocument.containsKey(docType)) {
                    tmpIDStore = LoanDocument.get(docType);
                }
                else {
                    tmpIDStore = new TreeSet<>();
                }
                tmpIDStore.add(applicationID);
                LoanDocument.put(docType, tmpIDStore);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            throw new IOException(
                    "Unable to open file " +
                            fileName );
        }
        catch(IOException ex) {
            throw new IOException(
                    "Unable to Read file " +
                            fileName );

        }
    }

    private Set<String> getDifference(Set<String> a, Set<String> b) {
        Set<String> result = new TreeSet<String>(a);
        for (String element : b) {
            if (!result.add(element)) {
                result.remove(element);
            }
        }
        return result;
    }

    public void findMissingLoan() {
        StringBuilder sb=new StringBuilder("");
        Set<String> value;
        String key;
        Set<String> tmpApplicationID;

        for (Map.Entry<String, Set<String>> entry : LoanDocument.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if(tmpApplicationID.size() != 0) {
                sb.append(key);
                sb.append("\n");
                sb.append(String.join(" ", tmpApplicationID));
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public void display() {
        System.out.println(LoanDocument);
    }


    public static void main(String[] args) throws IOException {

        solution sl = new solution("blendtest/input002.txt");
        sl.fileHandler();
        //sl.display();
        sl.findMissingLoan();
    }


}
