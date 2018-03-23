import java.util.ArrayList;
import java.util.List;

/*
Fibonacci Sequence
  F(n) = F(n-1) + F(n-2) n>2
  F(0) = 0
  F(1) =1
 Super Fibonacci Sequence
  F(n) = Sum ( F(n-i) ) i=1,K for n>k
  F[0..k] =[]

  K is a parameter for the series
 for k =2 it becomes normal Fib....

 Write a function FindIndex( int K , Array [] InitElements , int x)// Find position  index of x in the given series

 Example

k=2 , [0,1] THis means the series is 0,1,1,2,3,5,8,.....
      if x = 8 return 6
      if x = 6 return -1 6 is not in the series

k=3 [1,2,3]  series is 1,2,3,6,11,20,37.....
      x=3 return 2
      x=6 return 3
      x=12 return -1

k=3 [0,1,2]
k=3 [5,6,7]
 */

public class fibo {

    static int FindIndex(int k, int[] InitElements, int x) {

        int result = -1;
        int sum = 0, i = 0, j = k;
        List<Integer> store = new ArrayList<Integer>();
        if (k <= 0 ||
                InitElements == null ||
                k != InitElements.length)
            return result;

        for (int t = 0; t < k; t++) {
            if (InitElements[t] == x)
                return t;
            sum += InitElements[t];
            store.add(InitElements[t]);
        }
        store.add(sum);

        int firstSum ,lastSum, nextElement = sum;
        while (true && (sum != x) && (sum < x) || (sum < 0)) {


            firstSum = store.get(i);
            lastSum = store.get(j);
            sum =  sum + lastSum - firstSum;
            System.out.println(sum);
            store.add(sum);

            i += 1;
            j += 1;

        }
        if (sum == x)
            return j;
        return result;
    }

    public static void main(String args[]) {

        int[] arr= new int[]{1,2,3};
        System.out.println(FindIndex(3, arr, 20));
    }

}

