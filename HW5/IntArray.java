import java.util.Random;

/**
 * File Name: IntArray.java Infinite capacity int array
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2015
 */
/*
 * To compile you require: IntUtil.java RandomInt.java IntArray.java
 */

class IntArray {
  /*
   * ALL PRIVATE DATA BELOW
   */
  private int capacity;
  private int[] darray;
  static private boolean display = false;
  static IntUtil u = new IntUtil();

  /*
   * ALL PUBLIC ROUTINES BELOW
   */
  static void setDisplay(boolean x) {
    display = x;
  }

  public IntArray(int s) {
    allocate(s);
    if (display == true) {
      System.out.println("Creating darray of int of capacity " + capacity);
    }
  }
  
  public IntArray() {
    this(16); // This must be a first line
  }

  public int get(int pos) {
    if (pos < 0) {
      u.myassert(false);
      return -1;
    }
    if (pos < capacity) {
      return darray[pos];
    }
    grow(pos);
    return darray[pos];
  }

  public void set(int pos, int val) {
    if (pos < 0) {
      u.myassert(false);
    }
    if (pos >= capacity) {
      grow(pos);
    }
    darray[pos] = val;
  }
  
  public void swap(int a, int b) {
    int x = darray[a] ;
    darray[a] = darray[b] ;
    darray[b] = x ;
  }

  /*
   * ALL PRIVATES ROUTINES BELOW
   */

  private void allocate(int s) {
    capacity = s;
    darray = new int[s];
  }

  private void grow(int s) {
    int[] ta = darray;
    int ts = capacity ; //Old capacity
    int ns = capacity;
    do {
      ns = ns * 2;
    } while (ns <= s);

    if (display == true) {
      System.out.println("Array grew from " + ts + " to " + ns);
    }
    u.myassert(s < ns);
    allocate(ns);
    for (int i = 0; i < ts; ++i) {
      darray[i] = ta[i];
    }
    ta = null;
  }

  /*
   * if 'from' <= 'to' get elements from 'from' to 'to' else get elements from
   * 'to to 'from'
   */
  public int[] toarray(int from, int to) {
    if (from <= to) {
      int n = to - from + 1;
      int[] a = new int[n];
      int k = 0 ;
      for (int i = from; i <= to; ++i) {
        a[k++] = get(i);
      }
      return a;
    } else {
      int n = from - to + 1;
      int[] a = new int[n];
      int k = 0 ;
      for (int i = from; i >= to; --i) {
        a[k++] = get(i);
      }
      return a;
    }
  }

  /*
   * if 'from' <= 'to' print from 'from' to 'to' else print from 'to to 'from'
   */
  public void p(int from, int to) {
    if (from <= to) {
      for (int i = from; i <= to; ++i) {
        int x = get(i);
        System.out.print(x + " ");
      }
    } else {
      for (int i = from; i >= to; --i) {
        int x = get(i);
        System.out.print(x + " ");
      }
    }
  }

  public void pLn(int from, int to) {
    p(from, to);
    System.out.println();
  }

  public void p(String t, int from, int to) {
    System.out.print(t);
    p(from, to);
  }

  public void pLn(String t, int from, int to) {
    p(t, from, to);
    System.out.println();
  }

  /*
   * All test routines
   */

  private static void test1() {
    IntArray b = new IntArray();
    int s = 0 ;
    for (int i = 0; i < 8; ++i) {
      b.set(i, i * 10);
      ++s ;
    }
    b.pLn("from 0 to " + (s-1) + ": ", 0, s-1);
    b.pLn("from " + (s-1) + " to 0: ", s-1, 0);
    IntArray a = new IntArray();
    a.set(3, 300);
    a.set(56, 5600);
    int x = a.get(3);
    int y = a.get(56);
    int z = a.get(100);
    System.out.println("a[3]= " + x + " a[56] = " + y + " a[100] = " + z);
  }

  private static void fillRandom() {
    IntArray a = new IntArray();
    Random r = new Random();
    for (int i = 0; i < 100; ++i) {
      int v = RandomInt.getRandomInt(r, 1, 1000); // This gives number between 1
                                                  // to 999
      u.myassert(v != 0);
      a.set(v, i);
    }
    int nonzero = 0;
    for (int i = 0; i < 1000; ++i) {
      int x = a.get(i);
      if (x != 0) {
        nonzero++;
      }
      System.out.print(x + " ");
      if (i % 10 == 9) {
        System.out.println();
      }
    }
    System.out.println("nonzero = " + nonzero);
  }

  private static void testBench() {
    IntArray.setDisplay(true);
    System.out.println("------------test1---------------------");
    test1();
    System.out.println("------------fillRandom-----------------");
    fillRandom();
    System.out.println("------------test2----------------------");
  }

  public static void main(String[] args) {
    System.out.println("IntArray.java");
    testBench();
    System.out.println("Done");
  }
}