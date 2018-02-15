package com;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class treeAncestor {
    public static boolean haveSharedAncestor(List<List<Integer>> pairs, int nodeA, int nodeB) {
        if(pairs == null || pairs.size() == 0) {
            return false;
        }
        HashMap<Integer, List<Integer>> treeRelationMap = new HashMap<Integer, List<Integer>>();

        for(List<Integer> pair : pairs){
            int child = pair.get(1);
            int parent = pair.get(0);

            List<Integer> tempQ = treeRelationMap.get(child);
            if(tempQ == null) {
                tempQ = new ArrayList<Integer>();
            }
            tempQ.add(parent);
            treeRelationMap.put(child, tempQ);
        }

        Queue<Integer> childRelationQueue = new LinkedList<Integer>();
        childRelationQueue.offer(nodeA);
        HashSet<Integer> parentList = new HashSet<Integer>();

        while(!childRelationQueue.isEmpty()){
            int current = childRelationQueue.poll();
            if(treeRelationMap.containsKey(current)){
                for(int part : treeRelationMap.get(current)) {
                    parentList.add(part);
                    childRelationQueue.offer(part);
                }
            }
        }

        childRelationQueue.offer(nodeB);
        while(!childRelationQueue.isEmpty()){
            int curr = childRelationQueue.poll();
            if(treeRelationMap.containsKey(curr)){
                for(int part : treeRelationMap.get(curr)) {
                    if(parentList.contains(part))
                        return true;
                    childRelationQueue.offer(part);
                }
            }
        }
        return false;
    }

    // START TEST CASES
    //
    // You can add test cases below. Each test case should be an instance of Test
    // constructed with:
    //
    // Test(String name, List<List<Integer>> pairs, int nodeA, int nodeB, boolean expectedOutput);
    //

    private static final List<Test> tests = Arrays.asList(
            new Test(
                    // name
                    "sample input #1",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(3, 6),
                            Arrays.asList(5, 6),
                            Arrays.asList(5, 7),
                            Arrays.asList(4, 5),
                            Arrays.asList(4, 8),
                            Arrays.asList(8, 9)
                    ),
                    // nodeA
                    3,
                    // nodeB
                    8,
                    // expectedOutput
                    false
            ),
            new Test(
                    // name
                    "sample input #2",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(3, 6),
                            Arrays.asList(5, 6),
                            Arrays.asList(5, 7),
                            Arrays.asList(4, 5),
                            Arrays.asList(4, 8),
                            Arrays.asList(8, 9)
                    ),
                    // nodeA
                    5,
                    // nodeB
                    8,
                    // expectedOutput
                    true
            ),
            new Test(
                    // name
                    "sample input #3",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(3, 6),
                            Arrays.asList(5, 6),
                            Arrays.asList(5, 7),
                            Arrays.asList(4, 5),
                            Arrays.asList(4, 8),
                            Arrays.asList(8, 9)
                    ),
                    // nodeA
                    6,
                    // nodeB
                    8,
                    // expectedOutput
                    true
            ),
            new Test(
                    // name
                    "sample input #4",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(8, 9)
                    ),
                    // nodeA
                    6,
                    // nodeB
                    8,
                    // expectedOutput
                    false
            ),
            new Test(
                    // name
                    "sample input #5",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(8, 9)
                    ),
                    // nodeA
                    1,
                    // nodeB
                    8,
                    // expectedOutput
                    false
            ),

            new Test(
                    // name
                    "sample input #6",
                    // pairs
                    null,
                    // nodeA
                    1,
                    // nodeB
                    8,
                    // expectedOutput
                    false
            ),
            new Test(
                    // name
                    "sample input #5",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(4, 3)
                    ),
                    // nodeA
                    1,
                    // nodeB
                    4,
                    // expectedOutput
                    false
            ),
            new Test(
                    // name
                    "sample input #5",
                    // pairs
                    Arrays.asList(
                            Arrays.asList(1, 3),
                            Arrays.asList(2, 3),
                            Arrays.asList(3, 3)
                    ),
                    // nodeA
                    1,
                    // nodeB
                    4,
                    // expectedOutput
                    false
            )
    );


    // END TEST CASES
    // DO NOT MODIFY BELOW THIS LINE

    private static class Test {

        public String name;
        public List<List<Integer>> pairs;
        public int nodeA;
        public int nodeB;
        public boolean expectedOutput;

        public Test(String name, List<List<Integer>> pairs, int nodeA, int nodeB, boolean expectedOutput) {
            this.name = name;
            this.pairs = pairs;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.expectedOutput = expectedOutput;
        }
    }

    private static boolean equalOutputs(boolean a, boolean b) {
        return a == b;
    }

    public static void main(String[] args) {
        int passed = 0;
        for (Test test : tests) {
            try {
                System.out.printf("==> Testing %s...\n", test.name);
                boolean actualOutput = haveSharedAncestor(test.pairs, test.nodeA, test.nodeB);
                if (equalOutputs(actualOutput, test.expectedOutput)) {
                    System.out.println("PASS");
                    passed++;
                } else {
                    System.out.println("FAIL");
                    System.out.printf("Expected output: %s\n", test.expectedOutput);
                    System.out.printf("Actual output: %s\n", actualOutput);
                }
            } catch (Exception e) {
                System.out.println("FAIL");
                System.out.println(e);
            }
        }
        System.out.printf("==> Passed %d of %d tests\n", passed, tests.size());
    }
}
