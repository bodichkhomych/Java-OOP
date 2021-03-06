package com.univ.crypto.lab1;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

	private static final BigInteger ZERO = BigInteger.ZERO;
	private static final BigInteger ONE = BigInteger.ONE;
	private static final BigInteger TWO = new BigInteger("2");
	private static final BigInteger THREE = new BigInteger("3");

	public static boolean isProbablePrime(BigInteger n, int k) {
		if (n.compareTo(THREE) < 0)
			return true;
		int s = 0;
		BigInteger d = n.subtract(ONE);
		while (d.mod(TWO).equals(ZERO)) {
			s++;
			d = d.divide(TWO);
		}
		for (int i = 0; i < k; i++) {
			BigInteger a = uniformRandom(TWO, n.subtract(ONE));
			BigInteger x = a.modPow(d, n);
			if (x.equals(ONE) || x.equals(n.subtract(ONE)))
				continue;
			int r = 1;
			for (; r < s; r++) {
				x = x.modPow(TWO, n);
				if (x.equals(ONE))
					return false;
				if (x.equals(n.subtract(ONE)))
					break;
			}
			if (r == s) // None of the steps made x equal n-1.
				return false;
		}
		return true;
	}

	private static BigInteger uniformRandom(BigInteger bottom, BigInteger top) {
		Random rnd = new Random();
		BigInteger res;
		do {
			res = new BigInteger(top.bitLength(), rnd);
		} while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
		return res;
	}

	public static void main(String[] args) {
		// run with -ea to enable assertions
		String[] primes = { "1", "3", "3613", "7297",
				"226673591177742970257407", "2932031007403" };
		String[] nonPrimes = { "3341", "2932021007403",
				"226673591177742970257405" };
		int k = 40;
		for (String p : primes)
			assert isProbablePrime(new BigInteger(p), k);
		for (String n : nonPrimes)
			assert !isProbablePrime(new BigInteger(n), k);
	}
}
