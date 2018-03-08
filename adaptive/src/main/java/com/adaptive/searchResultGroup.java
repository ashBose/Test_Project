/*
Search engine would return a list of results, with docId, score, categoryId.
One way to improve KPI is to group items from different categories together, each group having only one item from each category. If a category runs out items, it is ok to skip that category.

Design an algorithm to implement the above functionality.


Example:
Id score categoryId
1, 10, 1
2, 9, 1
3, 8, 2
4, 7, 1
1, 2, 3 (by docId)

1st group: Id1 is the highest item from cateogry 1, Id3 is the highest item from category 2
2nd group/chunk: Id2 is the highest item from cateogry 1 in the remainging



=>
1, 3, 2 (by docId)
// (1, 3), (2)

*/


import java.util.*;
class Doc {

    int docId;
    int score;
    int ObjectId;

    Doc(int docId, int score, int ObjectId) {
     this.docId =  docId;
     this.score= score;
     this.ObjectId = ObjectId;
    }

    int getObjectId() {
        return ObjectId;
    }

    int getScore() {
        return score;
    }
}


public class eatest {
    List<Doc> store;
    Map<Integer, Queue<Doc>> finalOutput = new TreeMap<>(Collections.reverseOrder());

    eatest() {
        store = new ArrayList<>();
    }
    void set(Doc dc) {
        store.add(dc);
    }

    void doProcess() {
        for(Doc dc: store) {
            Queue<Doc> tmpQ= null;
            if(!finalOutput.containsKey(dc.getScore())) {
                tmpQ = new LinkedList<>();
            }
            else {
                tmpQ = finalOutput.get(dc.getScore());
            }
            tmpQ.add(dc);
            finalOutput.put(dc.getScore(), tmpQ);
        }

       // System.out.println(finalOutput);
        Queue<Integer> tmpQ = new LinkedList<>(finalOutput.keySet());
        Set<Integer> visited = new HashSet();
        List<Doc>docu = new ArrayList<>();

        while(!tmpQ.isEmpty()) {
            int topScore = tmpQ.remove();
            Queue<Doc> tmpDoc = finalOutput.get(topScore);
            if (visited.contains(topScore)) {
                visited = new HashSet();
                //System.out.println(docu);
                for(Doc n: docu) {
                    System.out.print(n.getObjectId() + " ");
                }
                System.out.println("\n");
                docu = new ArrayList<>();
            }
            if (!tmpDoc.isEmpty()) {
                docu.add(finalOutput.get(topScore).remove());
                visited.add(topScore);
                tmpQ.add(topScore);
            }
        }
    }

    public static void main(String[] args) {
        eatest et = new eatest();
        et.set(new Doc(1,10, 1));
        et.set(new Doc(2,10, 10));
        et.set(new Doc(9,10, 88));
        et.set(new Doc(3,9, 3));
        et.set(new Doc(4,9, 3));
        et.set(new Doc(5,9, 5));
        et.set(new Doc(6,8, 6));
        et.set(new Doc(7,7, 7));
        et.set(new Doc(8,6, 8));
        et.set(new Doc(81,6, 80));
        et.doProcess();
    }

}
