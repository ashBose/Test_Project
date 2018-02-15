import java.util.Arrays;
import java.util.List;

/*
Please solve "Write a program that prints the numbers from 1 to 100.
But for multiples of three print “Fizz” instead of the number and for the multiples of five print “Buzz”.
For numbers which are multiples of both three and five print “FizzBuzz” (Code must work out of the box.
 */

class program {

    public static boolean isMultiple(int number, int div) {
        if(number % div == 0) {
            return true;
        }
        return false;
    }

    public static String fizzBuzz(int number) {
        boolean hasFizzness = isMultiple(number, 3);
        boolean hasBuzzness = isMultiple(number, 5);

        String result;
        if (hasFizzness && hasBuzzness)
            result = "FizzBuzz";
        else if(hasFizzness)
            result = "Fizz";
        else if (hasBuzzness)
            result= "Buzz";
        else
            result = String.valueOf(number);
        return result;
    }

    public static void solution(int start, int end) {
        for(int i = start; i <= end; i++) {
            System.out.println(fizzBuzz(i));
        }
    }

    private static final List<Test> tests = Arrays.asList(
            new Test(
                    // name
                    "sample input #1",
                    1,
                    "1"

            ),
            new Test(
                    // name
                    "sample input #2",
                    3,
                    "Fizz"

            ),
            new Test(
                    // name
                    "sample input #3",
                    5,
                    "Buzz"

            ),
            new Test(
                    // name
                    "sample input #4",
                    15,
                    "FizzBuzz"

            ))
            ;
    private static class Test {

        public String name;
        public String expectedOutput;
        public int number;

        public Test(String name, int  number,String expectedOutput) {
            this.name = name;
            this.number = number;
            this.expectedOutput = expectedOutput;
        }
    }

    private static boolean equalOutputs(String a, String b) {
        return a.equals(b);
    }

    public static void main(String[] args) {
        solution(1, 100);
        for (Test test : tests) {
            try {
                System.out.printf("==> Testing %s...\n", test.name);
                String actualOutput = fizzBuzz(test.number);
                if (equalOutputs(actualOutput, test.expectedOutput)) {
                    System.out.println("PASS");
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
    }
}
