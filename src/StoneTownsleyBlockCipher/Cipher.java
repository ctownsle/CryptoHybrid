package StoneTownsleyBlockCipher;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import java.util.Random;

public class Cipher {

    private BitwiseSimplifications b;
    private byte [] ciphertext;

    public Cipher(){
        b = new BitwiseSimplifications();
    }

    public byte[] encrypt(final String message, final BigInteger rsaKey, final BigInteger rsaE){
        byte [] yaYEET = message.getBytes();
        byte [] symKey = generateKey(rsaKey, rsaE);
        ArrayList<byte []> yes = b.splitBytes(yaYEET);
        int counter = 1;
        byte [] finalArray = new byte[8 * yes.size()];
        for (byte [] by: yes) {
            for (int i = 0; i < 10; i++) { // ten rounds of computations
                by[0] = (byte) (b.leftRotate(by[0], 2) ^ symKey[0]);
                by[1] = (byte) (b.leftRotate(by[1], 3) ^ symKey[1]);
                by[2] = (byte)(b.rightRotate(by[2], 4) ^ symKey[2]);
                by[3] = (byte) (b.rightRotate(by[3], 2) ^ symKey[3]);
                by[4] = (byte) (b.rightRotate(by[4], 2) ^ symKey[4]);
                by[6] = (byte) (b.rightRotate(by[6], 5) ^ symKey[6]);
                by[5] = (byte) (b.leftRotate(by[5], 1) ^ symKey[5]);
                by[7] = (byte) (b.leftRotate(by[7], 4) ^ symKey[7]);
            }

            System.arraycopy(by, 0, finalArray, counter * 8 - 8, by.length);
            counter++;
        }

        return finalArray;
    }

    public byte[] decrypt(final byte[] message, final BigInteger rsaKey, final BigInteger rsaE){
        ArrayList<byte[]> bytes = b.splitBytes(message);
        byte[] symmKey = generateKey(rsaKey, rsaE);
        byte [] finalArray = new byte[8 * bytes.size()];
        int counter2 = 1;
        for (byte [] by: bytes) {
            for (int i = 0; i < 10; i++) {
                by[0] ^= symmKey[0];
                by[0] = b.rightRotate(by[0], 2);
                by[1] ^= symmKey[1];
                by[1] = b.rightRotate(by[1], 3);
                by[2] ^= symmKey[2];
                by[2] = b.leftRotate(by[2], 4);
                by[3] ^= symmKey[3];
                by[3] = b.leftRotate(by[3], 2);
                by[4] ^= symmKey[4];
                by[4] = b.leftRotate(by[4], 2);
                by[6] ^= symmKey[6];
                by[6] = b.leftRotate(by[6], 5);
                by[5] ^= symmKey[5];
                by[5] = b.rightRotate(by[5], 1);
                by[7] ^= symmKey[7];
                by[7] = b.rightRotate(by[7], 4);
            }

            System.arraycopy(by, 0, finalArray, counter2 * 8 - 8, by.length);
            counter2++;
        }
        return finalArray;
    }

    private byte[] generateKey(final BigInteger rsaKey, final BigInteger rsaE){
        Random r = new Random();
        //BigInteger d = BigInteger.probablePrime(256, r);
        byte [] res = rsaE.modPow(rsaE, rsaKey).toByteArray();
        byte [] key64B = new byte[8];

        System.arraycopy(res, 26, key64B, 0, key64B.length);
        long value = 0;
        for (int i = 0; i < key64B.length; i++) {
            value += ((long) key64B[i] & 0xffL) << (8 * i);
        }

        return key64B;
        //return value;
    }

    private byte [] longToBytes(long x){
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
}
