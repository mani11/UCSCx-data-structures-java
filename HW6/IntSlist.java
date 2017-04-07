import java.util.LinkedList;

/**
 * File Name: IntSlist.java Slist of int
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2017
 */
/*
 * To compile you require: Int.java IntUtil.java RandomInt.java IntSlist.java
 */

class IntSlist {
  /*
   * Private data members
   */
  private node first;
  private node last;
  private int num;
  private static final IntUtil u = new IntUtil();
  private static final int INTSLISTNULL = -99999999;

  private class node {
    private int d;
    private node next;

    private node(int x) {
      d = x;
      next = null;
    }
  }

  public IntSlist() {
    first = null;
    last = null;
    num = 0;
  }

  private void incSize() {
    ++num;
  }

  private void decSize() {
    --num;
  }

  /*
   * Returns the number of elements in this list.
   */
  public int size() {
    return num;
  }
  
  /*
   * Convert slist to array of integers
   */
  public int[] toArray() {
    int s = size() ;
    if (s != 0) {
      int [] a = new int[s] ;
      node t = first ;
      int i = 0 ;
      while (t != null) {
        a[i++] = t.d ;
        t = t.next ;
      }
      u.myassert(i == s) ;
      return a ;
    }else {
      return null ;
    }
  }

  /*
   * Appends int x to the end of this list.
   */
  public void add(int x) {
    node n = new node(x);
    if (first != null) {
      last.next = n;
    } else {
      first = n;
    }
    last = n;
    incSize();
  }

  /*
   * Returns the 'int' at the specified position in this list. pos should
   * between to 0 to (size-1) Returns INTSLISTNULL if 'pos' is wrong
   */
  public int get(int pos) {
    node t = first;
    int n = 0;
    while (t != null) {
      if (pos == n) {
        return t.d;
      }
      t = t.next;
      n++;
    }
    return INTSLISTNULL;
  }

  /*
   * Replaces the 'int' at the specified position in this list with the
   * specified element Returns false if 'pos' is wrong
   */
  public boolean set(int pos, int x) {

    return false;
  }

  /*
   * Returns first 'node' if this list contains the specified element. nodes[0]
   * is the node that contain x ; nodes[1] is the previous node before the
   * nodes[0](that has x)
   */
  private void find(int x, node[] nodes) {
    nodes[0] = first;
    nodes[1] = null;
    while (nodes[0] != null) {
      if (nodes[0].d == x) {
        return;
      }
      nodes[1] = nodes[0];
      nodes[0] = nodes[0].next;
    }
  }

  /*
   * Returns true if this list contains the specified element. More formally,
   * returns true if and only if this list contains at least one element
   */
  public boolean contains(int x) {
    node[] nodes = new node[2];
    find(x, nodes);
    return (nodes[0] != null) ? true : false;
  }

  /*
   * Removes the first occurrence of the specified element from this list, if it
   * is present. If this list does not contain the element, it is unchanged.
   * Returns false if 'x' is not removed
   */
  public boolean remove(int x) {
    //WRITE CODE HERE

    if (first == null) {
        return false;
    }

    if (first.d == x) {
        first = first.next;
        decSize();
        return true;
    }

    node t = first;
    while (t.next != null) {
        if (t.next.d == x) {
            t.next = t.next.next;
            decSize();
            return true;
        }
        t = t.next;
    }
    return false;
  }

  //O(1)
  public void addFront(int x){
    node n = new node(x);
    if (first == null) {
      first = n ;
      last = n ;
    }else {
      n.next = first ;
      first = n ;
    }
    incSize();
  }
  //O(1)
  public void addBack(int x){
    add(x) ;
  }
  //O(1)
  public boolean deleteFront() {
    if (first != null) {
      if (first == last) {
        //Only one element
        first = null ;
        last = null ;
      }else {
        //Many elements, but first one has to be removed
        first = first.next ;
      }
      decSize();
      return true ;
    }
    return false ;
  }
  //O(n) algorithm
  public boolean deleteLast() {
    if (first != null) {
      if (first == last) {
        //Only one element
        first = null ;
        last = null ;
      }else {
        //Many elements, but last one has to be removed
        node n = first ;
        while (n.next != last) {
          n = n.next ;
        }
        n.next = null ;
        last = n ;
      }
      decSize();
      return true ;
    }
    return false ;
  }


  /*
   * Build list of N elements n = 4. ascend = true 0 1 2 3 n = 4. ascend = false
   * 3 2 1 0 This is a factory
   */
  public static IntSlist buildSlist(int n, boolean ascend) {
    IntSlist l = new IntSlist();
    for (int i = 0; i < n; ++i) {
      int x = i;
      if (ascend == false) {
        x = n - 1 - i;
      }
      l.add(x);
    }
    return l;
  }

  /*
   * 0 1 2 is ok 1 0 2 is not ok
   */
  public void assertSlistInAscending() {
    node t = first;
    if (t != null) {
      node prev = t;
      node next = t.next;
      while (next != null) {
        if (next.d < prev.d) {
          u.myassert(false);
        }
        prev = next;
        next = next.next;
      }
    }
  }

  /*
   * 2 1 0 is ok 1 0 2 is not ok
   */
  public void assertSlistInDescending() {
    node t = first;
    if (t != null) {
      node prev = t;
      node next = t.next;
      while (next != null) {
        if (next.d > prev.d) {
          u.myassert(false);
        }
        prev = next;
        next = next.next;
      }
    }
  }

  /*
   * Reverse slist without using recursion Write code below
   */
  public void reverse() {
    //WRITE CODE HERE
    if (first == null) {
        return;
    }

    node pre = null, curr = first, next;
    while (curr != null) {
        next = curr.next;
        curr.next = pre;
        pre = curr;
        curr = next;
    }
    last = first;
    first = pre;
  }

  
  /*
   * Reverse slist using recursion Write code below
   */
  public void reverseRec() {
    //WRITE CODE HERE
    if (first == null || first.next == null) {
        return;
    }
    node t = first, res = first.next;
    first = first.next;
    reverseRec();
    t.next.next = t;
    t.next = null;
    first = last;
   }

  /*
   * Find the middle element of the Slist ODD (7 elements) ---- 0 1 2 3 4 5 6 |
   * size = 7. middle is 7/2 = 3. Mid element is in a[3]
   * 
   * EVEN (8 elements) --- 0 1 2 3 4 5 6 7 | size = 8. Middle is 8/2 = 4. Mid
   * element is in a[4]
   * 
   * You cannot use 'size()' or 'num' in this routine Return the middle element
   * in 'mid'. If list is empty return 0 Return the number of steps you took to
   * find middle elements in 'numsteps' Write code below
   */
  public void findMidPointOfSList(Int mid, Int numsteps) {
    // WRITE CODE BELOW. YOU CANNOT use 'size()' or 'num'
    // NOTE that mid and numsteps are of objects Int. Without objects you cannot send answers back
    mid.set(0);
    numsteps.set(0);

    if (first == null) {
        return;
    }

    node fast = first, slow = first;
    while (fast.next != null && fast.next.next != null) {
        numsteps.increment();
        fast = fast.next.next;
        slow = slow.next;
    }

    if (fast.next != null) {
        slow = slow.next;
    }

    mid.set(slow.d);
  }

  /*
   * Print a linked list
   */
  public void pLn() {
    node n = first;
    while (n != null) {
      System.out.print(n.d);
      if (n.next == null) {
        System.out.print("->NIL");
      } else {
        System.out.print("->");
      }
      n = n.next;
    }
    System.out.println("");
  }

  /* 
   * Print a linked list with title
   */
  public void pLn(String t) {
    System.out.print(t);
    pLn();
  }

  /*
   * this is roldgold Make sure roldgold is same as gold
   */
  public void assertGR(LinkedList<Integer> g) {
    int gs = g.size();
    if (gs != size()) {
      u.myassert(false);
    }
    int i = 0;
    node n = first;
    while (n != null) {
      u.myassert(n.d == (int) g.get(i));
      ++i;
      n = n.next;
    }
  }

  /*
   * Printing of testFindMidPointOfSList YOU CANNOT CHANGE ANYTHING IN THIS
   * ROUTINE
   */
  private void p1(Int mid, Int num) {
    if (size() < 10) {
      pLn("l[] = ");
    }
    if (num.get() == 0) {
      System.out.println("Empty list");
    } else {
      int v = mid.get();
      int ans = size() / 2;
      u.myassert(v == get(ans));
      System.out.println("size = " + size() + " Mid point value " + v
          + ". This was found after " + num.get() + " steps");
    }
  }

  /*
   * Test findMidPointOfSList() YOU CANNOT CHANGE ANYTHING IN THIS ROUTINE
   */
  private static void testFindMidPointOfSList() {
    IntSlist l = new IntSlist();
    Int mid = new Int();
    Int num = new Int();
    l.findMidPointOfSList(mid, num);
    l.p1(mid, num);
    l.add(1);
    l.findMidPointOfSList(mid, num);
    l.p1(mid, num);
    l.add(2);
    l.findMidPointOfSList(mid, num);
    l.p1(mid, num);
    for (int i = 3; i < 8; ++i) {
      l.add(i);
    }
    l.findMidPointOfSList(mid, num);
    l.p1(mid, num);
    l.add(8);
    l.findMidPointOfSList(mid, num);
    l.p1(mid, num);
    {
      for (int i = 1000; i < 5000; ++i) {
        IntSlist list = buildSlist(i, true);
        Int mid1 = new Int();
        Int num1 = new Int();
        list.findMidPointOfSList(mid1, num1);
        list.p1(mid1, num1);
      }
    }
  }

  /*
   * Test Reverse Slist
   */
  private static void testReverse() {
    IntSlist l = new IntSlist();
    for (int i = 0; i < 8; ++i) {
      l.add(i);
    }
    l.pLn("Before Reverse: ");
    l.reverse();
    l.pLn("After Reverse: ");
    l.reverseRec();
    l.pLn("After reverseRec: ");
    if (true) {
      IntSlist list = buildSlist(5000, true);
      list.assertSlistInAscending();
      list.reverseRec();
      list.assertSlistInDescending();
      System.out
          .println("---- list of ascending 5000 reverseREC passes ------------");
    }
    if (true) {
      IntSlist list1 = buildSlist(5001, false);
      list1.assertSlistInDescending();
      list1.reverseRec();
      list1.assertSlistInAscending();
      System.out
          .println("---- list of descending 5001 reverseREC passes ------------");
    }
    if (true) {
      IntSlist list2 = buildSlist(50001, false);
      list2.assertSlistInDescending();
      list2.reverse();
      list2.assertSlistInAscending();
      System.out
          .println("---- list of descending 50000001 reverse passes ------------");
    }
    if (true) {
      // RUNS FOR EVER WHY
      IntSlist list3 = buildSlist(50001, false);
      list3.assertSlistInDescending();
      list3.reverseRec();
      list3.assertSlistInAscending();
      System.out
          .println("---- list of descending 50000001 reverserec passes ------------");
    }
  }

  /*
   * YOU CANNOT CHANGE CODE in makeLoop
   */
  private void makeLoop() {
    for (int i = 0; i < 8; ++i) {
      add(i);
    }
    pLn("After adding 8 elements: ");
    node[] nodes = new node[2];
    find(1, nodes);
    node node1 = nodes[0];
    find(6, nodes);
    nodes[1].next = node1;
    // You can no longer print the slist
    // pLn("After loop 6->1 : ") ;
  }

  /*
   * WRITE CODE HERE TO DETECT LOOP YOU CANNOT USE 'size'
   */
  private boolean detectLoop() {
   //WRITE CODE HERE
    node fast = first, slow = first;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            return true;
        }
    }
    return false;
  }

  /*
   * You cannot change anything in this routine
   */
  private static void testDetectLoop() {
    IntSlist l = new IntSlist();
    l.makeLoop();
    // WRITE CODE BELOW. You cannot use 'size' or 'num'
    boolean x = l.detectLoop();
    if (x == true) {
      System.out.println("---- List has a loop ------------");
    } else {
      System.out.println("---- List has NO loop ------------");
    }
  }

  private static void test1() {
    IntSlist l = new IntSlist();
    for (int i = 0; i < 8; ++i) {
      l.add(i);
    }
    l.pLn("After adding 8 elements: ");
  }

  private static void testWithJavaLinkedList() {
    LinkedList<Integer> g = new LinkedList<>(); // gold
    IntSlist rg = new IntSlist(); // roldgold
    // add
    for (int i = 0; i < 100; ++i) {
      g.add(new Integer(i * 10)); // Note that we are adding Integer
      rg.add(i * 10);
    }
    rg.assertGR(g);
    System.out.println("---- passed after adding ------------");
    // test remove
    for (int i = 1; i < 1000; i = i * 10) {
      // public boolean remove(Object o)
      // g.remove(i); WRONG
      g.remove(new Integer(i)); // You need to give object
      rg.remove(i);
    }
    g.remove(new Integer(0)); // You need to give object
    rg.remove(0);
    g.remove(new Integer(990)); // You need to give object
    rg.remove(990);
    rg.assertGR(g);
    System.out.println("---- passed after removing ------------");
    rg.pLn("After removing: ");
  }

  private static void testbed() {
    test1();
    System.out.println("---- test1 passed ------------");
    testWithJavaLinkedList();
    System.out.println("---- testWithJavaLinkedList passed ------------");
    testFindMidPointOfSList();
    System.out.println("---- testFindMidPointOfSList passed ------------");
    testReverse();
    System.out.println("---- testReverse passed ------------");
    testDetectLoop();
    System.out.println("---- detectLoop passed ------------");
  }

  public static void main(String[] args) {
    System.out.println("IntSlist.java");
    testbed();
    System.out.println("Done");
  }
}
