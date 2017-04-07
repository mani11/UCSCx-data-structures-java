/**
 * File Name: BigUnsignedNumberTester.java 
 * This clas tests BigUnsignedNumber
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: BigUnsignedNumberTester.java BigUnsignedNumber.java  CharArray.java Cstring.java IntUtil.java
 */
import java.util.Random;
//NOTHING CAN BE CHANGED IN THIS FILE.
//If you have problem with name clone(), you can change clone() o klone()

class BigUnsignedNumberTester {
  static IntUtil u = new IntUtil();
  
  private static void testBasic() {
    BigUnsignedNumber a = new BigUnsignedNumber(789) ;
    a.pLn("a = ") ;
    BigUnsignedNumber b = new BigUnsignedNumber('7') ;
    b.pLn("b = ") ;
    BigUnsignedNumber c = new BigUnsignedNumber("123456789012345678901234567890123456789012345678901234567890") ;
    c.pLn("c = ") ;
    BigUnsignedNumber d = c.clone() ;
    d.pLn("d = ") ;
  }
  
  private static void testAdd() {
    BigUnsignedNumber a = new BigUnsignedNumber(9789) ;
    BigUnsignedNumber b = new BigUnsignedNumber(100000) ;
    a.pLn("a = ") ;
    b.pLn("b = ") ;
    BigUnsignedNumber c = a.add(b) ;
    c.pLn("a + b =  c = ") ;
    a = a.add(b) ;
    a.pLn("a = a + b = ") ;
    BigUnsignedNumber d = new BigUnsignedNumber("3490529510847650949147849619903898133417764638493387843990820577");
    BigUnsignedNumber e = new BigUnsignedNumber("32769132993266709549961988190834461413177642967992942539798288533");
    BigUnsignedNumber f = d.add(e) ;
    BigUnsignedNumber g = new BigUnsignedNumber("36259662504114360499109837810738359546595407606486330383789109110") ;
    u.myassert(f.isEqual(g));                                           
    d.pLn("d = ") ;
    e.pLn("e = ") ;
    f.pLn("f = ") ;
  }
  
  private static void testMult() {
    {
      BigUnsignedNumber a = new BigUnsignedNumber(9789) ;
      BigUnsignedNumber zero = new BigUnsignedNumber(0) ;
      BigUnsignedNumber one = new BigUnsignedNumber(1) ;
      a.pLn("a = ") ;
      zero.pLn("zero = ") ;
      one.pLn("one = ") ;
      BigUnsignedNumber b = a.mult(zero) ;
      b.pLn("b = a*0 =") ;
      BigUnsignedNumber c = zero.mult(a) ;
      c.pLn("c = 0*a = ") ;
      BigUnsignedNumber d = a.mult(one) ;
      d.pLn("d = a*1 = ") ;
      BigUnsignedNumber e = one.mult(a) ;
      e.pLn("e = 1*a =") ;
    }
    {
      BigUnsignedNumber a = new BigUnsignedNumber(9789) ;
      BigUnsignedNumber b = new BigUnsignedNumber(9) ;
      BigUnsignedNumber c = a.mult(b) ;
      a.pLn("a = ") ;
      b.pLn("b = ") ;
      c.pLn("c = ") ;
      u.myassert(c.isEqual("88101"));
      BigUnsignedNumber d = b.mult(a) ;
      d.pLn("d = ") ;
      u.myassert(d.isEqual("88101"));                    
    }
    {
      BigUnsignedNumber a = new BigUnsignedNumber("3490529510847650949147849619903898133417764638493387843990820577");
      BigUnsignedNumber b = new BigUnsignedNumber("32769132993266709549961988190834461413177642967992942539798288533");
      BigUnsignedNumber c = new BigUnsignedNumber("114381625757888867669235779976146612010218296721242362562561842935706935245733897830597123563958705058989075147599290026879543541");
      BigUnsignedNumber d = b.mult(a) ;
      a.pLn("a = ") ;
      b.pLn("b = ") ;
      c.pLn("c = ") ;
      d.pLn("d = ") ;
      u.myassert(d.isEqual(c)) ;
      System.out.println("Number of digits in a b and d = " + a.size()  + "  " + b.size() + "   " + c.size()) ;
    }
  }
  
   private static void testRandom() {
     int m = 1000 ;
     int max = (1 << 15) ;
     Random r = new Random();
     for (int i = 0 ; i < m ; ++i) {
       //System.out.println("i = " + i) ;
       int a = RandomInt.getRandomInt(r, 0, max);
       int b = RandomInt.getRandomInt(r, 0, max);
       BigUnsignedNumber ba = new BigUnsignedNumber(a) ;
       BigUnsignedNumber bb = new BigUnsignedNumber(b) ;
       BigUnsignedNumber ma = ba.add(bb) ;
       u.myassert(ma.isEqual(a+b)) ;
       BigUnsignedNumber mm = ba.mult(bb) ;
       u.myassert(mm.isEqual(a*b)) ;
     }
     System.out.println("Random addition and multiplication on " + m + " numbers passed") ;
   }
  
  private static void testFact() {
    BigUnsignedNumber fact100 = new BigUnsignedNumber("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000");
    BigUnsignedNumber b = BigUnsignedNumber.factorial(100) ;
    fact100.pLn("a = ") ;
    b.pLn("!100 = ") ;
    u.myassert(b.isEqual(fact100)) ;
    System.out.println("Number of digits in !100 = " + b.size()) ;
    
    BigUnsignedNumber fact1000 = new BigUnsignedNumber("402387260077093773543702433923003985719374864210714632543799910429938512398629020592044208486969404800479988610197196058631666872994808558901323829669944590997424504087073759918823627727188732519779505950995276120874975462497043601418278094646496291056393887437886487337119181045825783647849977012476632889835955735432513185323958463075557409114262417474349347553428646576611667797396668820291207379143853719588249808126867838374559731746136085379534524221586593201928090878297308431392844403281231558611036976801357304216168747609675871348312025478589320767169132448426236131412508780208000261683151027341827977704784635868170164365024153691398281264810213092761244896359928705114964975419909342221566832572080821333186116811553615836546984046708975602900950537616475847728421889679646244945160765353408198901385442487984959953319101723355556602139450399736280750137837615307127761926849034352625200015888535147331611702103968175921510907788019393178114194545257223865541461062892187960223838971476088506276862967146674697562911234082439208160153780889893964518263243671616762179168909779911903754031274622289988005195444414282012187361745992642956581746628302955570299024324153181617210465832036786906117260158783520751516284225540265170483304226143974286933061690897968482590125458327168226458066526769958652682272807075781391858178889652208164348344825993266043367660176999612831860788386150279465955131156552036093988180612138558600301435694527224206344631797460594682573103790084024432438465657245014402821885252470935190620929023136493273497565513958720559654228749774011413346962715422845862377387538230483865688976461927383814900140767310446640259899490222221765904339901886018566526485061799702356193897017860040811889729918311021171229845901641921068884387121855646124960798722908519296819372388642614839657382291123125024186649353143970137428531926649875337218940694281434118520158014123344828015051399694290153483077644569099073152433278288269864602789864321139083506217095002597389863554277196742822248757586765752344220207573630569498825087968928162753848863396909959826280956121450994871701244516461260379029309120889086942028510640182154399457156805941872748998094254742173582401063677404595741785160829230135358081840096996372524230560855903700624271243416909004153690105933983835777939410970027753472000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    long startTime = System.nanoTime();
    BigUnsignedNumber c = BigUnsignedNumber.factorial(1000) ;
    long endTime = System.nanoTime();
    double d = u.timeInSec(endTime,startTime) ;
    System.out.println("Run time for !1000 = " + d + " secs" );
    System.out.println("Number of digits in !1000 = " + c.size()) ;
    //Run time for !1000 = 0.225868438 secs 
    //Number of digits in !1000 = 2568
     fact1000.pLn("!1000 = ") ;
    c.pLn("b = ") ;
    u.myassert(c.isEqual(fact1000)) ;
  }
  
  private static void testBench() {
    System.out.println("-----------Basic----------------");
    testBasic() ;
    System.out.println("-----------Addition----------------");
    testAdd() ;
    System.out.println("-----------mult----------------");
    testMult() ;
    System.out.println("-----------random----------------");
    testRandom() ;
    System.out.println("-----------Factorial----------------");
    testFact() ;
  }

  public static void main(String[] args) {
    System.out.println("BigUnsignedNumberTester.java");
    testBench();
    System.out.println("Done");
  }
  
}
