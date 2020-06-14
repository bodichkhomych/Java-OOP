package com.univ.crypto.lab1;

import java.math.BigInteger;

public class ExtendedEuclid {

    public static BigInteger[] gcd(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO)) {
            return new BigInteger[]{
                    BigInteger.ZERO, BigInteger.ONE, b  //x,y,gcd
            };
        }

        BigInteger[] gcdRes = gcd(b.mod(a), a);  // x1, y1, gcd
        return new BigInteger[]{
                gcdRes[1].subtract(b.divide(a).multiply(gcdRes[0])), // x = 1 - x1 * b/a, y = x1
                gcdRes[0], gcdRes[2]
        };
    }

    public static void diofant(BigInteger a, BigInteger b, BigInteger c, BigInteger[] extEuclid) { //    ax(mod m) = b ==> ax + my = b

        if (!c.remainder(extEuclid[2]).equals(BigInteger.valueOf(0))) {
            System.out.println("None");
            return;
        }

        BigInteger x0 = extEuclid[0].multiply(c).divide(extEuclid[2]); // x0 = X * (c / gcd)
        BigInteger y0 = extEuclid[1].multiply(c).divide(extEuclid[2]); // y0 = Y * (c / gcd)
        System.out.println(c + " = " + x0 + "*" + a + " + " + y0 + "*" + b);

        System.out.println("x = " + x0);
        for (BigInteger k = BigInteger.valueOf(2); k.intValue() < 6; k = k.add(BigInteger.valueOf(1)))
            System.out.println("x = " + x0.add(b.multiply(k)));
    }

    public static void main(String[] args) {
        BigInteger a = new BigInteger("19");
        BigInteger b = new BigInteger("91");
        BigInteger c = new BigInteger("51");

        BigInteger[] res = gcd(a, b);
        System.out.println("GCD(" + a + ", " + b + ") = " + res[2]);
        System.out.println("" + res[0] + " = " + res[1] + "*" + a + " + " + res[2] + "*" + b);

        System.out.println("\nSolutions to Diofant equation: " + a + "*x(mod " + b + ") = " + c);
        diofant(a, b, c, res);
    }
}
