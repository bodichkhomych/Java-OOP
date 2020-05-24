package com.knu.lab3;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ElGamalAlgorithmTest {

    @Test
    public void encodeString() {
        String str1 = "First string to encode one";
        String str2 = "Second string to encode one";


        String hash1 = ElGamalAlgorithm.encode(str1);
        assertEquals(64, hash1.length());
        assertEquals(hash1, ElGamalAlgorithm.encode(str1));


        String hash2 = ElGamalAlgorithm.encode(str2);

        assertNotEquals(hash1, hash2);
    }

    @Test
    public void encodeFile() throws IOException {
        String inputPath = "input.txt";
        String outputPath = "output.txt";

        ElGamalAlgorithm.encode(inputPath, outputPath);

        FileReader readerInput = new FileReader(inputPath);
        FileReader readerOutput = new FileReader(outputPath);

        BufferedReader bufferedReaderInput = new BufferedReader(readerInput);
        BufferedReader bufferedReaderOutput = new BufferedReader(readerOutput);


        String line = bufferedReaderInput.readLine();
        String encodedLine = bufferedReaderOutput.readLine();

        assertEquals(64, encodedLine.length());
        assertEquals(encodedLine, ElGamalAlgorithm.encode(line));

        readerInput.close();
        readerOutput.close();
    }
}