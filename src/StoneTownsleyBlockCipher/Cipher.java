package StoneTownsleyBlockCipher;

import GUI.MyScreen;
import MessageAuth.HMAC;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;

public class Cipher {

    private BitwiseSimplifications b;
    private byte[] ciphertext;
    private byte [] symKey;
    private MyScreen screen;
    private HMAC h;
    private String aAuth, bAuth;

    public Cipher(final BigInteger rsaKey, final BigInteger rsaE, final MyScreen screen) throws InterruptedException{
        this.screen = screen;
        b = new BitwiseSimplifications();
        symKey = generateKey(rsaKey, rsaE);
        h = new HMAC();
    }

    public byte[] encrypt(final String message) throws InterruptedException{
        screen.addToListModel("BEGINNING ENCRYPTION");
        Thread.sleep(1000);
        byte [] yaYEET = message.getBytes();
        screen.addToListModel("CONVERTING MESSAGE TO 64 BIT BLOCKS (8 BYTE BLOCKS): ");
        ArrayList<byte []> yes = b.splitBytes(yaYEET);
        Thread.sleep(2000);
        for (byte [] b: yes) {
            screen.addToListModel(Arrays.toString(b));
        }
        Thread.sleep(2000);
        int counter = 1;
        byte [] finalArray = new byte[8 * yes.size()];
        screen.addToListModel("BEGINNING BLOCK CIPHER");
        screen.addToListModel("EACH BLOCK GOES THROUGH 6 ROUNDS");
        for (byte [] by: yes) {
            screen.addToListModel("BEGINNING MESSAGE BLOCK: " + counter);
            Thread.sleep(1000);
            for (int i = 0; i < 6; i++) { // ten rounds of computations
                screen.addToListModel("ROUND " + (i + 1) );
                Thread.sleep(2000);
                by[0] = (byte) (b.leftRotate(by[0], 1) ^ symKey[2]);
                by[1] = (byte) (b.leftRotate(by[1], 1) ^ symKey[0]);
                by[2] = (byte)(b.rightRotate(by[2], 1) ^ symKey[1]);
                by[3] = (byte) (b.rightRotate(by[3], 1) ^ symKey[7]);
                by[4] = (byte) (b.rightRotate(by[4], 1) ^ symKey[3]);
                by[6] = (byte) (b.rightRotate(by[6], 1) ^ symKey[4]);
                by[5] = (byte) (b.leftRotate(by[5], 1) ^ symKey[6]);
                by[7] = (byte) (b.leftRotate(by[7], 1) ^ symKey[5]);
                screen.addToListModel("RESULT: " + Arrays.toString(by));
            }

            System.arraycopy(by, 0, finalArray, counter * 8 - 8, by.length);
            counter++;
        }
        ciphertext = finalArray;
        StringBuilder sb = new StringBuilder();
        for (byte b: finalArray) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }
        screen.addToListModel("RESULTING CIPHERTEXT: " + sb.toString());
        //System.out.println(sb.toString());
        StringBuilder sbA = new StringBuilder();
        byte [] hashA = null;
        try {
            screen.addToListModel("HMAC ON CIPHERTEXT WITH SHARED SECRET KEY");
            Thread.sleep(2000);
            hashA = h.auth(finalArray, symKey);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        for(byte foo : hashA){
            String hex = String.format("%02x", foo);
            sbA.append(hex);
        }
        aAuth = sbA.toString();
        screen.addToListModel("RESULTING HASH MESSAGE: " + aAuth);
        return finalArray;
    }

    public byte[] decrypt(final byte[] message) throws InterruptedException{
        screen.addToListModel("BEGINNING AUTHENTICATION");
        screen.addToListModel("HMAC ON CIPHERTEXT WITH SHARED SECRET KEY");
        Thread.sleep(2000);
        byte [] bBytes = null;
        try {
            bBytes = h.auth(message, symKey);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        StringBuilder sbB = new StringBuilder();
        for(byte foo : bBytes){
            String hex = String.format("%02x", foo);
            sbB.append(hex);
        }
        bAuth = sbB.toString();
        screen.addToListModel("VERIFYING RECEIVER");
        Thread.sleep(2000);
        screen.addToListModel("CHECKING: " + aAuth + " VS. " + bAuth);
        Thread.sleep(2000);
        if(bAuth.equals(aAuth)) {
            screen.addToListModel("VERIFIED!!!");
            ArrayList<byte[]> bytes = b.splitBytes(message);
            byte[] finalArray = new byte[8 * bytes.size()];
            screen.addToListModel("CIPHERTEXT BROKEN INTO BLOCKS AGAIN");
            for (byte[] by : bytes) {
                screen.addToListModel(Arrays.toString(by));
            }
            Thread.sleep(2000);
            int counter2 = 1;
            for (byte[] by : bytes) {
                screen.addToListModel("BEGINNING MESSAGE BLOCK: " + counter2);
                Thread.sleep(1000);
                for (int i = 0; i < 6; i++) {
                    screen.addToListModel("ROUND " + (i + 1));
                    Thread.sleep(2000);
                    by[0] ^= symKey[2];
                    by[0] = b.rightRotate(by[0], 1);
                    by[1] ^= symKey[0];
                    by[1] = b.rightRotate(by[1], 1);
                    by[2] ^= symKey[1];
                    by[2] = b.leftRotate(by[2], 1);
                    by[3] ^= symKey[7];
                    by[3] = b.leftRotate(by[3], 1);
                    by[4] ^= symKey[3];
                    by[4] = b.leftRotate(by[4], 1);
                    by[6] ^= symKey[4];
                    by[6] = b.leftRotate(by[6], 1);
                    by[5] ^= symKey[6];
                    by[5] = b.rightRotate(by[5], 1);
                    by[7] ^= symKey[5];
                    by[7] = b.rightRotate(by[7], 1);
                    screen.addToListModel("RESULT: " + Arrays.toString(by));
                }

                System.arraycopy(by, 0, finalArray, counter2 * 8 - 8, by.length);
                counter2++;
            }
            screen.addToListModel("RESULTING TEXT: " + b.bytesToMessage(finalArray, false));
            return finalArray;
        } else return null;
    }

    private byte[] generateKey(final BigInteger rsaKey, final BigInteger rsaE) throws InterruptedException{
        screen.addToListModel("GENERATING SYMMETRIC KEY BASED ON ADDRESS");
        Random r = new Random();
        screen.addToListModel("CALCULATING d FOR SHARED SECRET KEY GENERATION");
        Thread.sleep(2000);
        BigInteger d = BigInteger.probablePrime(256, r);
        screen.addToListModel("d: " + new BigInteger(d.toString(), 10).toString(16));
        Thread.sleep(2000);
        screen.addToListModel("CALCULATING d modPow E, N");
        Thread.sleep(2000);
        byte [] res = d.modPow(rsaE, rsaKey).toByteArray();
        screen.addToListModel("RESULT: " + Arrays.toString(res));
        byte [] key64B = new byte[8];
        screen.addToListModel("PICK 64 BIT BLOCK OF RESULT AT RANDOM");
        System.arraycopy(res, r.nextInt(res.length - 64), key64B, 0, key64B.length);
        Thread.sleep(2000);
        screen.addToListModel("FINAL SHARED KEY: " + Arrays.toString(key64B));
        return key64B;
    }

    public byte[] getCiphertext() {
        return ciphertext;
    }

}
