package com.knu.lab3;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ElGamalAlgorithm.encode("input.txt", "output.txt");
    }
}
