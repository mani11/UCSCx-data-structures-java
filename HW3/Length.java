/**
 * HW 3
 * File Name: Length.java
 * Find the length that jump from s[x] to the position whose value is equals to x.
 *
 * @version 1.2
 * @author Wan-Shan Liao
 * @year 2017
 */
public class Length {

    private static final IntUtil u = new IntUtil();

    private static int length_easy(int [] s, int x) {
        int l = 0;
        int gx = x;
        while (true) {
            if (s[x] == gx) {
                return l;
            }
            x = s[x];
            ++l;
        }
    }

    /** Recursion version (1)
     *  Use integer to store x and s[x]
     *  Limit: array size must be less than 2^16 -1
     */
    private static int length_limit(int [] s, int x) {
        int xx = (x < 0xFFFF) ? x : ((x >> 16) & 0xFFFF) -1;
        int sx = x & 0xFFFF;
        if (s[sx] == xx)
            return 0;
        int y = (xx+1 << 16) + s[sx];
        return 1 + length(s, y);
    }


    /** Recursion version (2)
     *  Use swap twice
     */
    private static int length(int [] s, int x) {
        int sx = s[x], count, tmp;
        if (sx == x)
            return 0;

        // swap
        tmp = s[x];
        s[x] = s[sx];
        s[sx] = tmp;

        count = length(s, x);

        // swap
        tmp = s[x];
        s[x] = s[sx];
        s[sx] = tmp;

        return 1 + count;
    }

    private static void testbed() {
        int s[] = {5, 1, 0, 4, 2, 3};
        int y = length_easy(s, 3);
        System.out.println("length_easy y = " + y);
        u.myassert(y == 4);
        int b[] = {5, 1, 0, 4, 2, 3};
        int x = length(s, 3);
        System.out.println("length x = " + x);
        u.myassert(x == y);
        for (int i = 0; i < s.length; ++i) {
            u.myassert(s[i] == b[i]);
        }
        System.out.println("Assert passed");
    }

    public static void testbed1() {
        int s[] = {5, 1, 0, 4, 2, 3};
        int b[] = {5, 1, 0, 4, 2, 3};
        int l = s.length;
        for (int j = 0; j < l; ++j) {
            int y = length_easy(s, j);
            System.out.println("length_easy y = " + y);
            int x = length(s, j);
            System.out.println("length x = " + x);
            u.myassert(x == y);
            for (int i = 0; i < s.length; ++i) {
                u.myassert(s[i] == b[i]);
            }
        System.out.println("Assert passed");
        }
    }

    public static void main(String[] args) {
        System.out.println("Length.java");
        testbed();
        testbed1();
    }
}
