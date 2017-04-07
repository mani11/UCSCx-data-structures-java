
/**
 * File Name: BigUnsignedNumber.java 
 * Infinite capacity Unsigned Number
 * 
 * @author Jagadeesh Vasudevamurthy
 * @author Wan-Shan Liao
 * @year 2017
 */
/*
 * To compile you require: BigUnsignedNumber.java CharArray.java Cstring IntUtil.java
 */

class BigUnsignedNumber {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  
  public void pLn(String t) {
    d.pLn(t) ;
  }
  
  //WRITE ALL THE REQUIRED PROCEDURE REQUIRED TO COMPILE AND RUN BigUnsignedNumberTester.java 
  //NOTHING CAN BE CHANGED IN BigUnsignedNumberTester.java

  /** Default Constructor */
  public BigUnsignedNumber() {
    d = new Cstring();
  }

  /** Constructor that takes char */
  public BigUnsignedNumber(char ch) {
    d = new Cstring(ch);
  }

  /** Constructor that takes string */
  public BigUnsignedNumber(String str) {
    d = new Cstring(str);
  }

  /** Constructor that takes char[] */
  public BigUnsignedNumber(char[] arrChar) {
    d = new Cstring(arrChar);
  }

  /** Constructor that takes int */
  public BigUnsignedNumber(int num) {
    char[] chArray = String.valueOf(num).toCharArray();
    d = new Cstring(chArray);
  }

  /** Constructor that takes Cstring */
  public BigUnsignedNumber(Cstring cstr) {
    d = cstr;
  }

  /** Clone BigUnsignedNumber */
  public BigUnsignedNumber clone() {
    Cstring cstr = this.d.clone();
	BigUnsignedNumber bigNum = new BigUnsignedNumber(cstr);
    return bigNum;
  }

  /** Check two BigUnsigendNumber is equal or not */
  public boolean isEqual(BigUnsignedNumber bigNum) {
    return this.d.isEqual(bigNum.d);
  }

  /** Check the value of this BigUnsigendNumber and String is equal or not */
  public boolean isEqual(String str) {
    BigUnsignedNumber bigNum = new BigUnsignedNumber(str);
    return this.isEqual(bigNum);
  }

  /** Check the value of this BigUnsigendNumber and int is equal or not */
  public boolean isEqual(int num) {
    BigUnsignedNumber bigNum = new BigUnsignedNumber(num);
    return this.isEqual(bigNum);
  }

  /** Addition operation */
  public BigUnsignedNumber add(BigUnsignedNumber bigNum) {
	BigUnsignedNumber newNum = new BigUnsignedNumber();
    BigUnsignedNumber n1 = this.clone();
    BigUnsignedNumber n2 = bigNum.clone();
    int size1 = n1.size();
    int size2 = n2.size();
    int carry, digit;

	// Reverse two BigUnsignedNumber
	n1.d.reverse();
	n2.d.reverse();

	// set leading zero for smaller BigUnsignedNumber
	if (size1 > size2) {
		for (int i = 0; i < size1 - size2; ++i)
			n2.d.append("0");
	} else if (size1 < size2) {
		for (int i = 0; i < size2 - size1; ++i)
			n1.d.append("0");
	}

	// Digit adding 
	carry = 0;
	for (int i = 0; i < n1.size(); ++i) {
		digit = n1.d.get(i) + n2.d.get(i) + carry;
		if (digit > 9) {
			digit -= 10;
			carry = 1;
		} else {
			carry = 0;
		}
		newNum.d.append("" + digit);
	}
	if (carry > 0)
		newNum.d.append("" + carry);

	newNum.d.reverse();

	return newNum;
  }

  /** Multiplication operation */
  public BigUnsignedNumber mult(BigUnsignedNumber bigNum) {
	BigUnsignedNumber newNum = new BigUnsignedNumber(0);

    if (this.isEqual(0) || bigNum.isEqual(0))
        return newNum;

    BigUnsignedNumber n1 = this.clone();
    BigUnsignedNumber n2 = bigNum.clone();
    int size1 = n1.size();
    int size2 = n2.size();
    int carry = 0, digit;

	n2.d.reverse();

    for (int i = 0; i < n1.size(); ++i) {
        BigUnsignedNumber tempNum = new BigUnsignedNumber();
        carry = carry / 10;
        for (int j = 0; j < n2.size(); ++j) {
            digit = n1.d.get(i) * n2.d.get(j) + carry;
            carry = digit / 10;
            digit = digit % 10;
            tempNum.d.append("" + digit);
        }
        if (carry > 0)
            tempNum.d.append("" + carry);
        tempNum.d.reverse();

        if (n1.size() > 1 && !newNum.isEqual(0))
            newNum.d.append("0");
        newNum = newNum.add(tempNum);
    }
	return newNum;
  }


  /** Multiplication operation */
  /*
  public BigUnsignedNumber mult(BigUnsignedNumber bigNum) {
    int digit;
   	BigUnsignedNumber newNum = new BigUnsignedNumber(0);
    for (int i = 0; i < bigNum.size(); ++i) {
        digit = bigNum.d.get(i);
        for (int j = 0; j < digit; ++j) {
            newNum = this.add(newNum);
        }
        if (i < bigNum.size() - 1 && !newNum.isEqual(0))
            newNum.d.append("0");
    }
	return newNum;
  }
  */

  /** !num Factorial */
  public static BigUnsignedNumber factorial(int num) {
    BigUnsignedNumber one = new BigUnsignedNumber('1');
    BigUnsignedNumber factor = new BigUnsignedNumber(num);
    if (num == 1) {
        return one;
    }
    return factor.mult(factorial(num - 1));
  }

  /** Return the size of BigUnsignedNumber */
  public int size() {
      return this.d.size();
  }

}
