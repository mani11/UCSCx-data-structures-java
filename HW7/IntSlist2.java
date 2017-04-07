

/**
 * File Name: IntSlist2.java Slist of int
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2017
 */
/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java
 */

public class IntSlist2 {
  /*
   * protected data members
   */
  protected node first;
  //Cannot add last
  //Cannot add size
  //Cannot add anything here
  protected static final IntUtil u = new IntUtil();

   protected class node {
    //Cannot change anything in this class
    protected final int d; //You cannot change d or t
    protected final int t ;//Used for testing stable sort
    protected node next; //You should manipulate only next

    protected node(int x,int y) {
      d = x;
      t = y ;
      next = null;
    }
  }

  public IntSlist2() {
    first = null;
  }

  /*
   * Returns the number of elements in this list.
   */
  public int size() {
    //WRITE CODE
    node curr = first;
    int count = 0;
    while (curr != null) {
        count++;
        curr = curr.next;
    }
    return count;
  }

  /*
   * Appends int x to the end of this list.
   */
  public node add(int x,int y, node last) {
    //WRITE CODE
    node n = new node(x, y);
    if (first == null)
        first = n;
    else
        last.next = n;
    last = n;
    return n ;
  }
  
  /** Print Line */
  public void pLn() {
	node curr = first;
	while (curr != null) {
	  System.out.print(curr.d);
	  if (curr.next == null)
        System.out.print("->NIL");
	  else
		System.out.print("->");

	  curr = curr.next;
	}
    System.out.println("");
  }

  /** Print Line w/ string */
  public void pLn(String str){
    System.out.println(str);
    pLn();
  }

  /** Reverse the linked list */
  public void reverse(){
    node curr = first;
    node pre = null;
    node next = null;
    while (curr != null) {
      next = curr.next;
      curr.next = pre;
      pre = curr;
      curr = next;
    }
    first = pre;
  }

  /*
   * 0 1 2 is ok 1 0 2 is not ok
   * CANNOT CHANGE ROUTINE BELOW
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
        if (prev.d == next.d) {
          //assures stable sort
          u.myassert(prev.t < next.t);    
        }
        prev = next;
        next = next.next;
      }
    }
  }

  /*
   * 2 1 0 is ok 1 0 2 is not ok
   * CANNOT CHANGE ROUTINE BELOW
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
        if (prev.d == next.d) {
          //assures stable sort
          //Because we are not allowed to change data
          u.myassert(prev.t > next.t);    
        }
        prev = next;
        next = next.next;
      }
    }
  }
  
  /* Factory method. Build an slist from an array */
  //CANNOT CHANGE ROUTINE BELOW
  public static IntSlist2 buildSlist(int [] a) {
    IntSlist2 l = new IntSlist2();
    int n = a.length ;
    node last = null ;
    for (int i = 0; i < n; ++i) {
      int x = a[i] ;
      last = l.add(x,i,last);
    }
    return l;
  }

  protected static void test1() {
    IntSlist2 l = new IntSlist2();
    node last = null ;
    for (int i = 0; i < 8; ++i) {
      last = l.add(i,i,last);
    }
    l.pLn("After adding 8 elements: ");
    int [] a = {9, 6, 7, 10};
    IntSlist2 l2 = IntSlist2.buildSlist(a) ;
    l2.pLn("l2: ");
  }
 

  protected static void testbed() {
    test1();
    System.out.println("---- test1 passed ------------");
  }

  public static void main(String[] args) {
    System.out.println("IntSlist.java");
    testbed();
    System.out.println("Done");
  }
}
