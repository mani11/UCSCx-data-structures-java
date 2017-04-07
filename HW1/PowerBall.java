
/**
 * File Name: PowerBall.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2017
 */
class PowerBall {
  /*
   * ALL PRIVATE DATA BELOW
   * CANNOT ADD ANY MORE MEMBER
   */
  private int[] winningNumber ;
  private int[] ticketNumber ;
  private long cash ;
  static private boolean display = true ;
  static final long jackpot = 100000000;
  static private IntUtil u = new IntUtil();
  
  private void printNumbers() {
    if (display) {
      System.out.println("winningNumber ") ;
      u.pLn(winningNumber);
      System.out.println("ticketNumber  ") ;
      u.pLn(ticketNumber);
    }
  }
  
  public long cash() {
    return cash ;
  }
  
  private void check() {
    //WRITE CODE HERE
    //YOU CAN WRITE AS MANY PRIVATE ROUTINES AS YOU NEED
    int countMatch = 0, powerBall = 0;
    long [] prize = {0, 0, 0, 7, 100, 1000000, 4, 4, 7, 100, 50000, jackpot} ;

    try {
        u.myassert(ticketNumber.length == 6) ;
    } catch(Exception e){
        System.out.println("Invalid input: Length of ticketNumber must be 6") ;
        u.pLn(ticketNumber);
        return;
    }

    if (winningNumber[5] == ticketNumber[5]) {
        powerBall = 6;
    }

    for (int i = 0; i < winningNumber.length - 1; ++i) {
        for (int j = 0; j < ticketNumber.length - 1; ++j) {
            if (winningNumber[i] == ticketNumber[j]) {
                countMatch++ ;
                break ;
            }
        }
    }

    cash = prize[powerBall + countMatch] ;
  }
  
  PowerBall(int[] w, int [] t) {
    winningNumber = w ;
    ticketNumber = t ;
    cash = 0 ;
    check() ;
  }
  
  private static void test1() {
    //CANNOT CHANGE BELOW
    int[] w = {4,8,19,27,24,10} ;
    {
      int [] n= {4,8,19,27,24,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {24,27,19,8,4,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {24,27,19,8,4,5} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14,5} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,124,19,119,18,14} ;
      PowerBall x = new PowerBall(w,n);
    }  
  }
  
  private static void testRandom() {
  //CANNOT CHANGE BELOW
    System.out.println("----------testRandom()  starts-------------") ;
    display = false ;
    int[] w = {4,8,19,27,24,10} ;
    int max = 1000000 ;
    long c = 0 ;
    System.out.println("Buying " + max + " tickets of worth " + max * 2 +"$") ;
    for (int i = 0; i < max; ++i) {    
      int [] n = u.generateRandomNumber(6,true,1,60);
      PowerBall x = new PowerBall(w,n); 
      if (x.cash() == jackpot) {
        System.out.println("Won Jacckpot") ;
      }
      c = c + x.cash();
    }
    long p = (c -(max*2)) ;
    System.out.println("Out of " + max + " times you win " + c + "$") ;
    if (p > 0) {
      System.out.println("Profit = " + p) ;
    }else {
      System.out.println("Loss = " + p) ;
    }
    System.out.println("----------testRandom()  ends-------------") ;
  }
  
  private static void testBench() {
  //CANNOT CHANGE BELOW
    test1() ;
    testRandom() ;
  }
  
  public static void main(String[] args) {
  //CANNOT CHANGE BELOW
    System.out.println("PowerBall.java");
    testBench();
    System.out.println("Done");
  }
}
