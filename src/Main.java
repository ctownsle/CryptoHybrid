import MessageAuth.HMAC;
import RSA.Alice;
import RSA.Bob;
import RSA.RSA;
import StoneTownsleyBlockCipher.BitwiseSimplifications;
import StoneTownsleyBlockCipher.Cipher;

import java.security.NoSuchAlgorithmException;


public class Main {

    public static void main(String [] args) throws NoSuchAlgorithmException {
        //TODO: stuff
        Alice a  = new Alice();
        HMAC h = new HMAC(a, new Bob());

        RSA rsa = a.getRSAinstance();
        BitwiseSimplifications b = new BitwiseSimplifications();
        Cipher c = new Cipher(rsa.getN(), rsa.getE());
        System.out.println(b.bytesToMessage(c.decrypt(c.encrypt("Hello there, it appears that my name jeff")), false));
        System.out.println(new String(h.auth(c.getCiphertext(), "Hello tehre it seems that name jeff".getBytes())));
        System.out.println(new String(h.auth(c.getCiphertext(), "Hello tehre it seems that name jeff".getBytes())));
    }
}
