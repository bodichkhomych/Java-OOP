package com.knu.lab3;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void rotateLeft() {
        assertEquals(40, Util.rotateLeft(10, 2));
    }

    @Test
    public void rotateRight() {
        assertEquals(10, Util.rotateRight(40, 2));
    }

    @Test
    public void longToByteArray() {
        long value = 10;
        byte[] bytes = {0, 0, 0, 0, 0, 0, 0, 10};
        assertArrayEquals(bytes, Util.longToByteArray(value));
    }

    @Test
    public void byteArrayToInt() {
        byte[] bytes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(16909060, Util.byteArrayToInt(bytes, 0, 4));
    }

    @Test
    public void byteArrayToIntArray() {
        byte[] bytes = {1, 2, 3, 4};
        assertArrayEquals(new int[]{16909060}, Util.byteArrayToIntArray(bytes));
    }

    @Test
    public void intArrayToHexString() {
        int[] ints = {1, 2, 3, 4};
        assertEquals("00000001000000020000000300000004", Util.intArrayToHexString(ints));
    }
}