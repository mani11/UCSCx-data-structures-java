/**
 * File Name: TowerOfHanoi.java
 *
 * @author oneshan
 * @date 2017
 */

import java.util.Stack;

public class TowerOfHanoi {
    private int w = 0;
    public void resetKount() {w = 0; }
    public void incKount() {++w; }
    public void P() {System.out.println("Num move = "+ w); }
    TowerOfHanoi(int n, boolean recursive) {
        resetKount();
        if (recursive) {
            th_r(n, 1, 2, 3);
        } else {
            th(n, 1, 2, 3);
        }
    }

    private void th_r(int n, int src, int aux, int dst) {
        if (n > 0) { // at least one disk
            th_r(n-1, src, dst, aux); // Move n-1 from 'src' to 'aux' (using dst)
            incKount();
            System.out.println(src + "->" + dst);
            th_r(n-1, aux, src, dst); // Move n-1 from 'aux' to 'dst' (using src)
        }
    }

    private void th(int n, int src, int aux, int dst) {
        // WRITE YOUR CODE
        /** Total move of Honai (2^n -1)*/
        int totalMove = (1 << n) - 1;
        /** The top disk of two compared pole */
        Integer p1, p2;
        /** Top disk value of empty pole*/
        Integer empty = new Integer(n+1);
        /** Stack used to store disks in source pole */
        Stack<Integer> stack_src = new Stack<Integer>();
        /** Stack used to store disks in auxility pole */
        Stack<Integer> stack_aux = new Stack<Integer>();
        /** Stack used to store disks in dst pole */
        Stack<Integer> stack_dst = new Stack<Integer>();

        // Interchange aux & dst pole if n is even
        if ((n & 1) == 0) {
            int temp = aux;
            aux = dst;
            dst = temp;
        }

        // Initialize disks
        for (int i = n; i > 0; --i) {
            stack_src.push(new Integer(i));
        }

        for (int i = 0; i < totalMove; ++i) {
            incKount();
            // Compare and move top disk of src and dst
            if (i % 3 == 0) {
                p1 = stack_src.isEmpty() ? empty : stack_src.peek();
                p2 = stack_dst.isEmpty() ? empty : stack_dst.peek();
                if (p1 < p2) {
                    stack_dst.push(stack_src.pop());
                    System.out.println(src + " -> " + dst);
                } else {
                    stack_src.push(stack_dst.pop());
                    System.out.println(dst + " -> " + src);
                }
            }
            // Compare and move top disk of src and aux
            else if (i % 3 == 1) {
                p1 = stack_src.isEmpty() ? empty : stack_src.peek();
                p2 = stack_aux.isEmpty() ? empty : stack_aux.peek();
                if (p1 < p2) {
                    stack_aux.push(stack_src.pop());
                    System.out.println(src + " -> " + aux);
                } else {
                    stack_src.push(stack_aux.pop());
                    System.out.println(aux + " -> " + src);
                }
            }
            // Compare and move top disk of aux and dst
            else if (i % 3 == 2) {
                p1 = stack_aux.isEmpty() ? empty : stack_aux.peek();
                p2 = stack_dst.isEmpty() ? empty : stack_dst.peek();
                if (p1 < p2) {
                    stack_dst.push(stack_aux.pop());
                    System.out.println(aux + " -> " + dst);
                } else {
                    stack_aux.push(stack_dst.pop());
                    System.out.println(dst + " -> " + aux);
                }
            }
        }
    }

    private static void testBench() {
        for (int i = 1; i < 10; ++i) {
            System.out.println("--------" + i + "--------");
            {
                TowerOfHanoi t = new TowerOfHanoi(i, true);
                t.P();
            }
            {
                TowerOfHanoi t = new TowerOfHanoi(i, false);
                t.P();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("TowerOfHanoi.java");
        testBench();
        System.out.println("TowerOfHanoi.java DONE");
    }
}
