/**
 * File Name: SlistSort.java 
 * Base class
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java
 */
 
// CANNOT CHANGE ANYTHING IN THIS FILE
abstract class SlistSort extends IntSlist2{
  protected IntSlist2 a ; //slist to be sorted
  protected long numCompare ;
  protected long numSwap ;
  protected int numRecursion ;
  protected boolean display;
  protected static final IntUtil u = new IntUtil();
  
  private void sort1(IntSlist2 a,boolean ascend) {
   System.out.println("---------SORT1 Start------------"); 
    this.a = a ;
    numCompare = 0 ;
    numSwap = 0 ;
    numRecursion = 0 ;
    display = false ;
    int os = a.size() ;
    if (os > 0 && os < 20) {
      display = true ;
    }
    sort(ascend); //THIS CODE IS WRITTEN BY USER
    int ns = a.size();
    u.myassert(os == ns);
    if (ascend) {
      a.assertSlistInAscending() ;
    }else {
      this.a.reverse() ;
      a.assertSlistInDescending() ;
    }  
    if (display) {
      a.pLn() ;
    }
    u.printStatistics(a.size(),numCompare,numSwap,0);
    System.out.println("---------SORT1 End---------------");
  }

  //I don't know how to write it
  //Override by the concrete class
  abstract protected void sort(boolean ascend) ;
  
  private  void testSort(int N, boolean ascend) {
    int [] a = u.generateRandomNumber(N,false,1,N/5);//So that lots of same numbers are hit
    IntSlist2 s =  IntSlist2.buildSlist(a);
    sort1(s,ascend) ;
  }
  
  private  void basicTests() {
    int b[][] = u.testArray();
    int l = b.length ;
    for (int i = 0; i < l; ++i) {
      int [] a = b[i] ;
      u.pLn("a = ", a) ;
      IntSlist2 s =  IntSlist2.buildSlist(a);
      sort1(s,true) ;
    }
  }

  protected void testBench() {
    System.out.println("------------START---------------------");
    basicTests() ;
    for (int n = 10000; n < 50000; n = n + 10000) {
      testSort(n,true); //ascending order
    }
    for (int n = 10001; n < 50001; n = n + 10000) {
      testSort(n,false); //descending order
    }
    int h = 1 ;
    for (int i = 5000 + h; i < 25001 + h; i = i + 5000) {  
      System.out.println("------------testing  " + i + " SORTED ASCENDING numbers----------"); 
      int [] b = u.generateNumberInIncreasingOrder(i,1) ;
      IntSlist2 s =  IntSlist2.buildSlist(b);
      sort1(s,true) ;
    } 
    System.out.println("------------DONE!--------");
  }
 
  public static void main(String[] args) {
    System.out.println("ArraySort.java");
    //You cannot instantiate an object from abstract class
    //ArraySort a = new ArraySort() ;
    //a.testBench();
  }
}
