import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class cyence {

    public final String domain = ".com";
    HashMap<String, Integer> emailMap;
    public final String delimiter = "; ";
    List<String> outputList;

    cyence() {
        emailMap = new HashMap<String, Integer>();
    }

    public static List<String> emailSplit(String str, String delim){
        return Stream.of(str.split(delim))
                .map (elem -> new String(elem).replaceAll("^\\s*|\\s*$", "").toLowerCase())
                .collect(Collectors.toList());
    }

    public String solution(String X, String Y) {
        String emailDomain = "@" + Y.toLowerCase() +  domain;
        outputList = new ArrayList<>();
        String hashKey;
        String tmpEmail = null;
        for(String val: emailSplit(X, ";")) {
            List<String> names = emailSplit(val, "\\s+");
            if (names.size() == 0 || names.size() == 1)
                continue;
            hashKey = names.get(0) + ".";
            hashKey +=  names.size() > 2? names.get(2):names.get(1);
            tmpEmail = hashKey;
            if(emailMap.containsKey(hashKey)) {
                int c = emailMap.get(hashKey);
                tmpEmail += String.valueOf(c);
                emailMap.put(hashKey, c + 1);
            }
            else {
                emailMap.put(hashKey, 1);
            }
            outputList.add(tmpEmail + emailDomain);
        }

        return String.join(delimiter, outputList);

    }


    public static void main(String[] args) {

        String S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
        String Y = "Example";

        cyence c = new cyence();
        System.out.println(c.solution(S, Y));

    }

}
