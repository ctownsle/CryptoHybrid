package StoneTownsleyBlockCipher;

import java.math.BigInteger;
import java.util.ArrayList;

import java.util.Random;

public class Cipher {

    private BitwiseSimplifications b;
    private String ciphertext;
    private byte [] symKey;
    public Cipher(final BigInteger rsaKey, final BigInteger rsaE){
        b = new BitwiseSimplifications();
        symKey = generateKey(rsaKey, rsaE);
    }

    public byte[] encrypt(final String message){
        byte [] yaYEET = message.getBytes();
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
        ciphertext = b.bytesToMessage(finalArray, true);
        System.out.println(b.bytesToMessage(finalArray, true));
        return finalArray;
    }

    public byte[] decrypt(final byte[] message){
        ArrayList<byte[]> bytes = b.splitBytes(message);
        byte [] finalArray = new byte[8 * bytes.size()];
        int counter2 = 1;
        for (byte [] by: bytes) {
            for (int i = 0; i < 10; i++) {
                by[0] ^= symKey[0];
                by[0] = b.rightRotate(by[0], 2);
                by[1] ^= symKey[1];
                by[1] = b.rightRotate(by[1], 3);
                by[2] ^= symKey[2];
                by[2] = b.leftRotate(by[2], 4);
                by[3] ^= symKey[3];
                by[3] = b.leftRotate(by[3], 2);
                by[4] ^= symKey[4];
                by[4] = b.leftRotate(by[4], 2);
                by[6] ^= symKey[6];
                by[6] = b.leftRotate(by[6], 5);
                by[5] ^= symKey[5];
                by[5] = b.rightRotate(by[5], 1);
                by[7] ^= symKey[7];
                by[7] = b.rightRotate(by[7], 4);
            }

            System.arraycopy(by, 0, finalArray, counter2 * 8 - 8, by.length);
            counter2++;
        }
        return finalArray;
    }

    private byte[] generateKey(final BigInteger rsaKey, final BigInteger rsaE){
        Random r = new Random();
        BigInteger d = BigInteger.probablePrime(256, r);
        byte [] res = d.modPow(rsaE, rsaKey).toByteArray();
        byte [] key64B = new byte[8];

        System.arraycopy(res, 26, key64B, 0, key64B.length);

        return key64B;
    }

    public String getCiphertext() {
        return ciphertext;
    }

}
