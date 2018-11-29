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
        long symKey = generateKey(rsaKey, rsaE);
        ArrayList<byte []> yes = b.splitBytes(yaYEET);
        int counter = 1;
        int counter2 = 0;
        for (byte [] by: yes) {
            for (int i = 0; i < 10; i++) { // ten rounds of computations
                by[0] = b.leftRotate(by[0], 2);
                by[1] = b.leftRotate(by[1], 3);
                by[2] = b.rightRotate(by[2], 4);
                by[3] = b.rightRotate(by[3], 2);
                by[4] = b.rightRotate(by[4], 2);
                by[6] = b.rightRotate(by[6], 5);
                by[5] = b.leftRotate(by[5], 1);
                by[7] = b.leftRotate(by[7], 4);
            }
            long messageValue = 0;
            for (int i = 0; i < by.length; i++) {
                messageValue += ((long) by[i] & 0xffL) << (8 * i);
            }
            long res = messageValue ^ symKey;
            yes.set(counter2, longToBytes(res)); //longToBytes(res);
            counter2++;
        }
        byte [] finalArray = new byte[8 * yes.size()];
        for (byte[] by: yes) {
            System.arraycopy(by, 0, finalArray, counter * 8 - 8, by.length);
            counter++;
        }

        return finalArray;
    }

    public byte[] decrypt(final byte[] message, final BigInteger rsaKey, final BigInteger rsaE){
        ArrayList<byte[]> bytes = b.splitBytes(message);
        long symmKey = generateKey(rsaKey, rsaE);

        int counter = 0;
        for (byte [] by: bytes) {
            long messageValue = 0;
            for (int i = 0; i < by.length; i++) {
                messageValue += ((long) message[i] & 0xffL) << (8 * i);
            }
            long res = messageValue ^ symmKey;
            byte [] byteRes = longToBytes(res);
            bytes.set(counter, byteRes);
            counter++;
            for (int i = 0; i < 10; i++) {
                by[0] = b.rightRotate(by[0], 2);
                by[1] = b.rightRotate(by[1], 3);
                by[2] = b.leftRotate(by[2], 4);
                by[3] = b.leftRotate(by[3], 2);
                by[4] = b.leftRotate(by[4], 2);
                by[6] = b.leftRotate(by[6], 5);
                by[5] = b.rightRotate(by[5], 1);
                by[7] = b.rightRotate(by[7], 4);
            }
        }
        byte [] finalArray = new byte[8 * bytes.size()];
        int counter2 = 1;
        for (byte[] by: bytes) {
            System.arraycopy(by, 0, finalArray, counter2 * 8 - 8, by.length);
            counter2++;
        }
        return finalArray;
    }

    private long generateKey(final BigInteger rsaKey, final BigInteger rsaE){
        Random r = new Random();
        //BigInteger d = BigInteger.probablePrime(256, r);
        byte [] res = rsaE.modPow(rsaE, rsaKey).toByteArray();
        byte [] key64B = new byte[8];

        System.arraycopy(res, 26, key64B, 0, key64B.length);
        long value = 0;
        for (int i = 0; i < key64B.length; i++) {
            value += ((long) key64B[i] & 0xffL) << (8 * i);
        }
        return value;
    }

    private byte [] longToBytes(long x){
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        byte [] arr = buffer.array();
        return arr;
    }
}
