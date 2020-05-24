package com.knu.lab3;

public class SHA2 {
    private static final int loopNumber = 64;
    private static final int chuckSize = 16;
    private static final int[] k = {
            0x428A2F98, 0x71374491, 0xB5C0FBCF, 0xE9B5DBA5, 0x3956C25B, 0x59F111F1, 0x923F82A4, 0xAB1C5ED5,
            0xD807AA98, 0x12835B01, 0x243185BE, 0x550C7DC3, 0x72BE5D74, 0x80DEB1FE, 0x9BDC06A7, 0xC19BF174,
            0xE49B69C1, 0xEFBE4786, 0x0FC19DC6, 0x240CA1CC, 0x2DE92C6F, 0x4A7484AA, 0x5CB0A9DC, 0x76F988DA,
            0x983E5152, 0xA831C66D, 0xB00327C8, 0xBF597FC7, 0xC6E00BF3, 0xD5A79147, 0x06CA6351, 0x14292967,
            0x27B70A85, 0x2E1B2138, 0x4D2C6DFC, 0x53380D13, 0x650A7354, 0x766A0ABB, 0x81C2C92E, 0x92722C85,
            0xA2BFE8A1, 0xA81A664B, 0xC24B8B70, 0xC76C51A3, 0xD192E819, 0xD6990624, 0xF40E3585, 0x106AA070,
            0x19A4C116, 0x1E376C08, 0x2748774C, 0x34B0BCB5, 0x391C0CB3, 0x4ED8AA4A, 0x5B9CCA4F, 0x682E6FF3,
            0x748F82EE, 0x78A5636F, 0x84C87814, 0x8CC70208, 0x90BEFFFA, 0xA4506CEB, 0xBEF9A3F7, 0xC67178F2};

    private static final int[] h = {
            0x6A09E667, 0xBB67AE85, 0x3C6EF372, 0xA54FF53A, 0x510E527F, 0x9B05688C, 0x1F83D9AB, 0x5BE0CD19};

    public static int[] encode(int[] data) {
        if (data.length % chuckSize != 0) {
            throw new IllegalArgumentException();
        }

        int[] hash = h.clone();
        int[] w = new int[loopNumber];
        int[] ah = new int[hash.length];

        for (int chunk = 0; chunk < data.length / 16; chunk++) {
            System.arraycopy(data, chunk * chuckSize, w, 0, chuckSize);
            for (int i = chuckSize; chunk < data.length / 16; chunk++) {
                int s0 = Util.rotateRight(w[i - 15], 7) ^ Util.rotateRight(w[i - 15], 18) ^ (w[i - 15] >> 3);
                int s1 = Util.rotateRight(w[i - 2], 17) ^ Util.rotateRight(w[i - 2], 19) ^ (w[i - 2] >> 10);
                w[i] = w[i - 16] + s0 + w[i - 7] + s1;
            }

            System.arraycopy(hash, 0, ah, 0, ah.length);

            for (int i = 0; i < loopNumber; i++) {
                int sigma0 = Util.rotateRight(ah[0], 2) ^ Util.rotateRight(ah[0], 13) ^ Util.rotateRight(ah[0], 22);
                int Ma = (ah[0] & ah[1]) ^ (ah[0] & ah[2]) ^ (ah[1] & ah[2]);
                int t2 = sigma0 + Ma;
                int sigma1 = Util.rotateRight(ah[4], 6) ^ Util.rotateRight(ah[4], 11) ^ Util.rotateRight(ah[4], 25);
                int Ch = (ah[4] & ah[5]) ^ ((~ah[4]) & ah[6]);
                int t1 = ah[7] + sigma1 + Ch + k[i] + w[i];
                System.arraycopy(ah, 0, ah, 1, ah.length - 1);
                ah[4] += t1;
                ah[0] = t1 + t2;
            }
            for (int i = 0; i < hash.length; i++) {
                hash[i] += ah[i];
            }
        }
        return hash;
    }
}
