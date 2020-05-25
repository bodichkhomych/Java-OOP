package com.knu.lab3;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

public class Key {
    private static final int CERTAINTY = 90;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Key(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public static Key generateKey(int keyBitLength) throws Exception {
        Random random = new Random(new Date().getTime());
        BigInteger p = new BigInteger(keyBitLength, CERTAINTY, random);
        BigInteger g = primitiveRootModulo(p);
        BigInteger x = new BigInteger(keyBitLength - 1, CERTAINTY, new Random(new Date().getTime()));
        BigInteger y = g.modPow(x, p);

        return new Key(new PublicKey(y, g, p), new PrivateKey(x));
    }

    private static BigInteger primitiveRootModulo(BigInteger p) throws Exception {
        BigInteger eulerFucntionValue = p.subtract(BigInteger.ONE);
        BigInteger i = BigInteger.ZERO;
        while (i.compareTo(eulerFucntionValue) <= 0) {
            if (i.modPow(eulerFucntionValue, p).compareTo(BigInteger.ONE) == 0) {
                return i;
            }
            i = i.add(BigInteger.ONE);
        }

        throw new Exception("NOT FOUND");
    }


    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
