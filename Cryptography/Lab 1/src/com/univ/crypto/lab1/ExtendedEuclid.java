package com.univ.crypto.lab1;
import java.math.*;

public class ExtendedEuclid {

    public static void main(String[] args) {
        BigInteger a = new BigInteger("15");
        BigInteger b = new BigInteger("11");
        BigInteger[] res = ExtendedEuclid(a, b);
        System.out.println("GCD(" + a.toString()
                            + ", " + b.toString()
                            + ") = " + res[0].toString());

        System.out.println(res[0].toString() + " = "
                + res[1].toString() + "*" + a.toString()
                + " + " + res[2].toString() + "*" + a.toString());

    }

    public static BigInteger[] ExtendedEuclid(BigInteger a, BigInteger b) {

        BigInteger[] ans = new BigInteger[3];


        if (b.intValue() == 0)  {  /*  If b = 0, then we're done...  */
            ans[0] = a;
            ans[1] = BigInteger.valueOf(1);
            ans[2] = BigInteger.valueOf(0);
        }
        else {
            /*  Otherwise, make a recursive function call  */
            BigInteger q = a.divide(b);
            ans = ExtendedEuclid (b, a.remainder(b));
            BigInteger temp = ans[1].subtract(ans[2].multiply(q));
            ans[1] = ans[2];
            ans[2] = temp;
        }

        return ans;
    }

}

