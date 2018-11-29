import RSA.Alice;
import RSA.RSA;
import StoneTownsleyBlockCipher.BitwiseSimplifications;
import StoneTownsleyBlockCipher.Cipher;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static void main(String [] args){
        //TODO: stuff
        Alice a  = new Alice();

        RSA rsa = a.getRSAinstance();
        BitwiseSimplifications b = new BitwiseSimplifications();

        byte[] yeet = "Hello there, it appears that my name jeff".getBytes();
        ArrayList<byte []> jeff = b.splitBytes(yeet);
        byte jeff7 = b.rightRotate((byte)15, 3);
        byte jeffi9 = b.leftRotate(jeff7, 3);
        Cipher c = new Cipher();
        c.decrypt(c.encrypt("Hello there, it appears that my name jeff", rsa.getN(), rsa.getE()), rsa.getN(), rsa.getE());
    }
}
