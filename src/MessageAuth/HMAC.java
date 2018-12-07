package MessageAuth;

import RSA.Alice;
import RSA.Bob;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HMAC {

    private Alice a;
    private Bob b;
    private static int blockSize = 32;

    public HMAC(){

    }

    public byte[] auth(final byte [] cipherText, final byte [] sharedSecret) throws NoSuchAlgorithmException{
        byte [] fixed = sharedSecret;
        if (sharedSecret.length > blockSize){
            hash(new String(cipherText));
        } else {
            fixed = Arrays.copyOfRange(sharedSecret, 0, blockSize);
        }

        BigInteger oKeyPad = new BigInteger(fixed).xor(BigInteger.valueOf(0x5c * blockSize));
        BigInteger iKeyPad = new BigInteger(fixed).xor(BigInteger.valueOf(0x36 * blockSize));


        byte [] iKeyBytes = iKeyPad.toByteArray();
        byte [] oKeyBytes = oKeyPad.toByteArray();
        int length = cipherText.length + iKeyBytes.length;
        byte [] resA = new byte[length];
        System.arraycopy(iKeyBytes, 0, resA, 0, iKeyBytes.length);
        System.arraycopy(cipherText, 0, resA, iKeyBytes.length, cipherText.length);
        byte [] hash1 = hash(new String(resA));
        int length2 = oKeyBytes.length + hash1.length;
        byte [] finalH = new byte[length2];
        System.arraycopy(oKeyBytes, 0, finalH, 0, oKeyBytes.length);
        System.arraycopy(hash1, 0, finalH, oKeyBytes.length, hash1.length);

        byte [] finalHash = hash(new String(finalH));
        return finalHash;

    }

    private byte [] hash(final String cipherText) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(cipherText.getBytes(StandardCharsets.UTF_8));
    }
}
