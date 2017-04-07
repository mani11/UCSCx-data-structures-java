/**
 * File Name: TruthTable.java
 *
 * @author Wan-Shan Liao
 * @year 2017
 */

public class TruthTable {

    /** Recursion version
     *  Complexity: O(2^n) 
     */
    static int step = 0;   
    public static void printTable(int n, String s) {
        if (n == 0) {
            step++; 
            System.out.println(s);
            return;
        }
        printTable(n-1, s + "0");
        printTable(n-1, s + "1");
    }

    /** For-loop version 
     *  Complexity: O(n * 2^n)
     */
    public static void printTable_loop(int nInput, int total) {
        for (int i = 0; i < total; ++i) {
            int mask = 1 << nInput-1;
            for (int j = 0; j < nInput; ++j) {
                if ((i & mask) == 0)
                    System.out.print(0);
                else
                    System.out.print(1);
                mask = mask >> 1;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int nInput = 30;

        // for-loop & bitwise version
        System.out.printf("---- Truth table of %d inputs function ----\n", nInput);
        int total = 1 << nInput;
        printTable_loop(nInput, total);
        System.out.printf("For %d inputs, table size is %d\n", nInput, total);

        //  recursion version
        System.out.printf("---- Truth table of %d inputs function ----\n", nInput);
        printTable(nInput, "");
        System.out.printf("For %d inputs, table size is %d\n", nInput, step);
    }
}
