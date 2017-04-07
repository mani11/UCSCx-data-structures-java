/**
 * File Name: Cstring.java 
 * Implements C String
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2016
 */
/*
 * To compile you require: CharArray.java Cstring
 */

class Cstring {
  private CharArray d; //Infinte array of char
  static IntUtil u = new IntUtil();
  
  //WRITE ALL THE REQUIRED CODE BELOW
  /** Default Constructor */
  public Cstring() {
    d = new CharArray();
  }

  /** Constructor that takes char */
  public Cstring(char ch) {
    d = new CharArray();
    d.set(0, ch);
    d.set(1, '\0');
  }

  /** Constructor that takes char[] */
  public Cstring(char[] arrChar) {
    d = new CharArray();
    int i = 0;
    for (char ch: arrChar) {
      d.set(i++, ch);
    }
    d.set(i, '\0');
  }

  /** Constructor that takes string */
  public Cstring(String str) {
    d = new CharArray();
    int i = 0;
    for (char ch: str.toCharArray()) {
      d.set(i++, ch);
    }
    d.set(i, '\0');
  }

  /** Println for Csting */
  public void pLn(String str) {
    for (char ch: str.toCharArray()) {
      System.out.print(ch);
    }
    for (int i = 0; i < this.size(); ++i) {
        System.out.print(this.d.get(i));
    }
    System.out.println();
  }

  /** Clone Cstring */
  public Cstring clone() {
    char[] arrChar = new char[this.size()];
    for (int i = 0; i < this.size(); ++i) {
        arrChar[i] = this.d.get(i);
    }
    Cstring cstr = new Cstring(arrChar);
    return cstr;
  }

  /** Add another Cstring and return a new Cstring */
  public Cstring add(Cstring cstr) {
    char[] arrChar = new char[this.size() + cstr.size() + 1];
    for (int i = 0; i < this.size(); ++i) {
        arrChar[i] = this.d.get(i);
    }
    for (int i = 0; i < cstr.size(); ++i) {
        arrChar[i + this.size()] = cstr.d.get(i);
    }
    Cstring newCstr = new Cstring(arrChar);
    return newCstr;
  }

  /** Add string and return a new Cstring */
  public Cstring add(String str) {
    Cstring cstr = new Cstring(str);
    return this.add(cstr);
  }

  /** Append another Cstring and return this */
  public Cstring append(Cstring cstr) {
    int length = this.size();
    for (int i = 0; i < cstr.size() ; ++i) {
        this.d.set(length + i, cstr.d.get(i));
    }
    return this;
  }

  /** Append a string and return this */
  public Cstring append(String str) {
    Cstring cstr = new Cstring(str);
    return this.append(cstr);
  }

  /** Reverse Cstring */
  public void reverse() {
    int i = 0;
    int j = this.size() - 1;
    while (i < j) {
        this.d.swap(i, j);
        ++i;
        --j;
    }
    d.set(this.size(), '\0');
  }

  /** Check two Cstring is equal or not */
  public boolean isEqual(Cstring cstr) {
      if (this.size() != cstr.size())
          return false;
      for (int i = 0; i < this.size() ; ++i) {
          if (this.d.get(i) != cstr.d.get(i))
              return false;
      }
      return true;
  }

  /** Return the size of Cstring array */
  public int size() {
    int length = 0;
    while (this.d.get(length++) != '\0');
    return length - 1;
  }

  /** Return the integer value at index*/
  public int get(int index) {
    return this.d.get(index) - '0';
  }

  /** Set the value at index */
  public void set(int index, int value) {
    this.d.set(index, (char) value);
  }


  //NOTHING CAN BE CHANGED BELOW. EVERYTHING MUST WORK AS IS
  private static void testBasic() {
    Cstring a = new Cstring('b') ;
    a.pLn("a = ") ;
    Cstring b = new Cstring('7') ;
    b.pLn("b = ") ;
    Cstring c = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    c.pLn("c = ") ;
    Cstring d = c.clone() ;
    d.pLn("d = ") ;
    Cstring e = new Cstring("A quick brown fox junped over a lazy dog") ;
    e.pLn("e = ") ;
    Cstring f = new Cstring("Gateman sees name garageman sees nametag") ;
    f.pLn("f =  ") ;
    f.reverse() ;
    f.pLn("f' = ") ;
  }
  
  private static void testAdd() {
    Cstring a = new Cstring("UCSC") ;
    Cstring b = new Cstring("Extension") ;
    Cstring c = a.add(b) ;
    a.pLn("a = ") ;
    b.pLn("b = ") ;
    c.pLn("c = ") ;
    Cstring d = c.add("USA") ;
    d.pLn("d = ") ;
    a.append(b) ;
    a.pLn("a+b = ") ;
    a.append("World") ;
    a.pLn("a+b+World = ") ;
  }
  
  private static void testEqual() {
    Cstring a = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    a.pLn("a = ") ;
    Cstring b = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    b.pLn("b = ") ;
    u.myassert(a.isEqual(b)) ;
    Cstring c = new Cstring("12345678901234567890123456789012345678901234567890123456789") ;
    c.pLn("c = ") ;
    u.myassert(a.isEqual(c) == false) ;
  }
  
  private static void testBench() {
    System.out.println("-----------Basic----------------");
    testBasic() ;
    System.out.println("-----------Addition----------------");
    testAdd() ;
    System.out.println("-----------Equal----------------");
    testEqual() ;
  }
  
  public static void main(String[] args) {
    System.out.println("Cstring.java");
    testBench();
    System.out.println("Done");
  }
  
}
