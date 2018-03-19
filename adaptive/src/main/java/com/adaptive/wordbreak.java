public class wordbreak {

public static List<String> wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.isEmpty())
            return Collections.EMPTY_LIST;
        return wordBreak1(s, wordDict, new HashMap());
    }

    public static List<String> wordBreak1(String s, Set<String> wordDict,
                                          Map<String,List<String> > map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List res = new ArrayList();
        for (int i = 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(0, i))) {
                if (s.substring(i).length() > 0) {
                    List<String> r = wordBreak(s.substring(i), wordDict);
                    String w = s.substring(0, i);
                    if (!r.isEmpty()) {
                        for (String a : r) {
                            res.add(w + " " + a);
                        }
                    }
                } else {
                    res.add(s.substring(0, i));
                }
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String[] args) {
        Set<String>br = new HashSet<>();
        br.add("cat");
        br.add("cats");
        br.add("dog");
        br.add("and");
        br.add("dogs");
        br.add("s");

        List<String>op = new ArrayList<>();
        String input = "catsanddogs";
        System.out.println(wordBreak(input, br));
    }

}
