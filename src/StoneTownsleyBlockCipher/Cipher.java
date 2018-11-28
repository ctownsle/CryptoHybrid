package StoneTownsleyBlockCipher;

import java.math.BigInteger;
import java.util.ArrayList;

public class Cipher {

    private BitwiseSimplifications b;

    public Cipher(){
        b = new BitwiseSimplifications();
    }

    public byte[] encrypt(final String message, final BigInteger rsaKey, final BigInteger rsaE){
        long reasonable = generateKey();
        byte [] yaYEET = message.getBytes();
        ArrayList<byte []> yes = b.splitBytes(yaYEET);
        for (byte [] by: yes) {
            //for (int i = 0; i < 12; i++) { // twelve rounds of computations
                by[0] ^= 0xAB;
                by[1] = b.leftRotate(by[1], 2);
                by[2] ^= 0xAB >>> 5;
                by[3] = b.rightRotate(by[3], 4);
                by[4] = (byte) (b.rightRotate(by[4], 3) >> 8 * yes.size() ^ 0xFF);
                by[6] ^= 0xAB << 3;
                by[5] ^= 0xAB ^ 0xFF;
                by[7] = b.leftRotate(by[7], 6);
            //}

        }
        byte [] finalArray = new byte[8 * yes.size()];
        int counter = 1;
        for (byte[] by: yes) {
            System.arraycopy(by, 0, finalArray, counter * 8 - 8, by.length);
            counter++;
        }
        return finalArray;
    }

    public byte[] decrypt(final byte[] message){
        ArrayList<byte[]> bytes = b.splitBytes(message);
        for (byte[] by: bytes) {
            //for (int i = 0; i < 12; i++) {
                by[0] ^= 0xAB;
                by[1] = b.rightRotate(by[1], 2);
                by[2] ^= 0xAB >>> 5;
                by[3] = b.leftRotate(by[3], 4);
                by[4] = (byte) (b.leftRotate(by[4], 3) << message.length ^ 0xFF);
                by[5] ^= 0xAB ^ 0xFF;
                by[6] ^= 0xAB << 3;
                by[7] = b.rightRotate(by[7], 6);
            //}
        }
        byte [] finalArray = new byte[8 * bytes.size()];
        int counter = 1;
        for (byte[] by: bytes) {
            System.arraycopy(by, 0, finalArray, counter * 8 - 8, by.length);
            counter++;
        }
        return finalArray;
    }

    private long generateKey(){
        return 0;
    }
}
