package com.knu.lab3;

import java.nio.ByteBuffer;

public class Util {
    public static byte[] longToByteArray(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(value);
        return buffer.array();
    }

    public static int byteArrayToInt(byte[] bytes, int offset, int length) {
        return ByteBuffer.wrap(bytes, offset, length).getInt();
    }

    public static int[] byteArrayToIntArray(byte[] bytes) {
        int[] result = new int[(bytes.length + 3) / 4];
        for (int i = 0; i < result.length; i++) {
            result[i] = byteArrayToInt(bytes, i * 4, 4);
        }
        return result;
    }

    public static String intArrayToHexString(int[] value) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            long longValue = Integer.toUnsignedLong(value[i]);
            stringBuilder.append(String.format("%08x", longValue));
        }
        return stringBuilder.toString();
    }

    public static int rotateLeft(int value, int bits) {
        return (value << bits) | (value >>> (Integer.SIZE - bits));
    }

    public static int rotateRight(int value, int bits) {
        return (value >>> bits) | (value << (Integer.SIZE - bits));
    }
}
