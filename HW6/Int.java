/**
 * File Name: Int.java Mutable int object
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2015
 */

class Int {
  /*
   * Private data members
   */
  private int x;

  public Int(int n) {
    x = n;
  }

  public Int() {
    this(0); // This must be first
  }

  public int get() {
    return x;
  }

  public void set(int n) {
    x = n;
  }

  public void increment() {
    x++;
  }

  public void decrement() {
    x--;
  }

  public void swap(Int o) {
    int t = x;
    x = o.x;
    o.x = t;
  }

  public void p() {
    System.out.print(x);
  }

  public void pLn() {
    p();
    System.out.println();
  }

  public void pLn(String t) {
    System.out.print(t);
    pLn();
  }
  
  private static void swap1(Int x, Int y) {
    x.pLn("start swap1 x= ") ;
    y.pLn("start swap1 y= ");
    Int t = x ;
    x.set(y.get()) ;
    y.set(t.get());
    x.pLn("end swap1 x= ") ;
    y.pLn("end swap1 y= ");
  }
  
  private static void testSwap1() {
    Int x  = new Int(10);
    Int y = new Int(20) ;
    x.pLn("start testSwap1 x= ") ;
    y.pLn("start testSwap1 y= ");
    swap1(x,y) ;
    x.pLn("end testSwap1 x= ") ;
    y.pLn("end testSwap1 y= ");
  }
  
  private static void swap2(Int x, Int y) {
    x.pLn("start swap2 x= ") ;
    y.pLn("start swap2 y= ");
    Int t = new Int(x.get()) ;
    x.set(y.get()) ;
    y.set(t.get());
    x.pLn("end swap2 x= ") ;
    y.pLn("end swap2 y= ");
  }
  
  private static void testSwap2() {
    Int x  = new Int(10);
    Int y = new Int(20) ;
    x.pLn("start testSwap2 x= ") ;
    y.pLn("start testSwap2 y= ");
    swap2(x,y) ;
    x.pLn("end testSwap2 x= ") ;
    y.pLn("end testSwap2 y= ");
  }
   
  private static void test2() {
    System.out.println("---------testSwap1----------");
    testSwap1();
    System.out.println("---------testSwap2----------");
    testSwap2();
  }
  
  private static void test1() {
    Int x = new Int(56);
    x.pLn("x = ");
    Int y = new Int(8);
    y.pLn("y = ");
    x.swap(y);
    x.pLn("afterswap x = ");
    y.pLn("afterswap y = ");
  }
  
  private static void testbed() {
    System.out.println("---------test1----------");
    test1() ;
    System.out.println("---------test2----------");
    test2()  ;
  }

  public static void main(String[] args) {
    System.out.println("Int.java");
    testbed();
    System.out.println("Done");
  }
}