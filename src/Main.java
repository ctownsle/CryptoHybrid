import RSA.Alice;
import StoneTownsleyBlockCipher.BitwiseSimplifications;
import StoneTownsleyBlockCipher.Cipher;

import java.math.BigInteger;
import java.util.ArrayList;

public class Main {

    public static void main(String [] args){
        //TODO: stuff
        Alice a  = new Alice();

        BitwiseSimplifications b = new BitwiseSimplifications();

        byte[] yeet = "Hello there, it appears that my name jeff".getBytes();
        ArrayList<byte []> jeff = b.splitBytes(yeet);
        byte jeff7 = b.rightRotate((byte)15, 3);
        byte jeffi9 = b.leftRotate(jeff7, 3);
        System.out.println(jeff7);
        System.out.println(jeffi9);

        Cipher c = new Cipher();
        c.decrypt(c.encrypt("Hello there, it appears that my name jeff", BigInteger.ONE, BigInteger.ONE));
    }
}
