/**
 * File Name: AmicablePair.java
 * Find all amicable pairs in the range
 *
 * Amicable Pairs (m, n): the sum of proper divisors of each 
 * is equal to the other number, for example: (220, 284).
 *
 * @author Wan-Shan Liao
 * @year 2017
 */

import java.util.ArrayList;
import java.util.List;

public class AmicablePair {
    /** Maximum of range*/
    private int max ;
    /** Execute time of full functions */
    private double duration;
    /** Amicable list */
    private List<int[]> pairs;
    /** Prime list */
    private ArrayList<Integer> prime;
    
    /** Constructor */
    public AmicablePair(int n) { 
        max = n;
        pairs = new ArrayList<int[]>();
        prime = new ArrayList<Integer>();
    }

    /** Generate prime list using Sieve of Eratosthenes */
    public void generatePrime() {
        boolean [] a = new boolean [max + 1];
        a[1] = true;

        // only generate primes which is less than or equal to sqrt(max)
        for (int i = 2; i * i <= max; i++) {
            if (!a[i]) {
                prime.add(i);
                int multiple = 2;
                while (i * multiple <= max) {
                    a[i * multiple++] = true;
                }
            }
        }
    }

    /** Measure CPU time (pdf page 33) */
    double timeInSec(long endTime, long startTime) {
        long duration = (endTime - startTime);
        if (duration > 0) {
            double dm = (duration/1000000.0);  // Milliseconds
            double d = dm/1000.0;  //seconds
            return d ;
        }
        return 0.0 ;
    }

    /** Sum factors of integer n using prime factorization */
    private int sumFactors(int n) {
        int sum = 1, temp = n;
        for (int p: prime) {
            if (p > temp) break;
            int inner_sum = 1, count = 1;
            while (temp % p == 0) {
                inner_sum += Math.pow(p, count++);
                temp /= p;
            }
            sum *= inner_sum;
        }
        // temp is a prime factor if temp > 1
        if (temp > 1) {sum *= (1+temp);}
        return sum - n;
    }

    /** Find all amicable pairs in the range (2, max)  */
    public void getPair() {
        for (int i = 2; i <= max; ++i) {
            int sum = sumFactors(i);
            if (sum > i && sum <= max) {
                int sum2 = sumFactors(sum);
                if ( i == sum2 ) {
                    pairs.add(new int[] {i, sum});
                }
            }
        } 
    }

    /** Run functions and print result */
    public void run() {
        long startTime = System.nanoTime();
        generatePrime();
        getPair();

        System.out.printf("Amicable pairs MAX = %d\n", max);
        System.out.println("The following are amicable numbers");
        int count = 0;
        for (int[] arr : pairs) {
            System.out.printf("%d: %d and %d\n", count++, arr[0], arr[1]);
        }

        long endTime = System.nanoTime();
        duration = timeInSec(endTime,startTime) ;
        System.out.printf("Run time for n = %d is %.2f secs\n", max, duration);
    }

    public static void main(String[] args) {
        int n = 100000000;
        AmicablePair a = new AmicablePair(n);
        a.run();
    }
}
