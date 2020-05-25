package com.knu.lab3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Encryptor {
    private Key key = null;

    public Encryptor(Key key) {
        this.key = key;
    }


    public ArrayList<Message> encryptString(String s) throws Exception {
        ArrayList<Message> ciphertextMessages = new ArrayList<>();

        byte[] byteInput = s.getBytes();
        byte[] byteInputStub = new byte[1];
        for (byte b : byteInput) {
            byteInputStub[0] = b;
            ciphertextMessages.add(this.encrypt(byteInputStub));
        }

        return ciphertextMessages;
    }

    public String decryptString(ArrayList<Message> ciphertextMessages) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Message m : ciphertextMessages) {
            stringBuffer.append(new String(this.decrypt(m)));
        }

        return stringBuffer.toString();
    }

    public Message encrypt(byte[] message) throws Exception {
        BigInteger y = key.getPublicKey().getKeyY();
        BigInteger g = key.getPublicKey().getKeyG();
        BigInteger p = key.getPublicKey().getKeyP();

        int randomK = generateRandomKByP(p);
        BigInteger messageBI = new BigInteger(message);
        BigInteger a = g.modPow(BigInteger.valueOf(randomK), p);
        BigInteger b = ((y.pow(randomK)).multiply(messageBI)).mod(p);

        return new Message(a.toByteArray(), b.toByteArray());
    }

    public byte[] decrypt(Message message) {
        BigInteger y = key.getPublicKey().getKeyY();
        BigInteger g = key.getPublicKey().getKeyG();
        BigInteger p = key.getPublicKey().getKeyP();
        BigInteger x = key.getPrivateKey().getKey();
        BigInteger a = new BigInteger(message.getA());
        BigInteger b = new BigInteger(message.getB());

        BigInteger degree = p.subtract(x).subtract(BigInteger.ONE);
        BigInteger decrypted = (b.multiply(bigIntPowBrute(a, degree))).mod(p);

        return decrypted.toByteArray();
    }

    private int generateRandomKByP(BigInteger p) throws Exception {
        int bitLength = Math.min(32, p.bitLength());
        Random random = new Random(new Date().getTime());

        //guaranteed k > 0 (at least 2), and in range of int size
        if (p.bitLength() > 32) {
            return random.nextInt(Integer.MAX_VALUE - 2) + 2;
        } else {
            int pInt = p.intValue();
            if (pInt < 3) {
                throw new Exception("LESS THAN 3");
            }
            return Math.abs(random.nextInt(pInt - 3)) + 2;
        }
    }

    private static BigInteger bigIntPowBrute(BigInteger base, BigInteger exp) {
        BigInteger i = BigInteger.ONE;
        BigInteger product = BigInteger.ONE;
        for (; i.compareTo(exp) <= 0; i = i.add(BigInteger.ONE)) {
            product = product.multiply(base);
        }

        return product;
    }
}
