package java;

import com.knu.lab3.Encryptor;
import com.knu.lab3.Key;

public class Main {
    public static void main(String[] args) throws Exception {
        Key key =  Key.generateKey(16);
        Encryptor encryptor = new Encryptor(key);

        String plaintext = "Encoded stirng text";
        System.out.println(encryptor.decryptString(encryptor.encryptString(plaintext)));
    }
}
