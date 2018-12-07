import GUI.MyScreen;

import java.security.NoSuchAlgorithmException;


public class Main {

    public static void main(String [] args) throws NoSuchAlgorithmException {
        //TODO: stuff
        MyScreen jeff = new MyScreen();

       /* Alice a  = new Alice();
        HMAC h = new HMAC(a, new Bob());

        RSA rsa = a.getRSAinstance();
        BitwiseSimplifications b = new BitwiseSimplifications();
        Cipher c = new Cipher(rsa.getN(), rsa.getE());
        System.out.println(b.bytesToMessage(c.decrypt(c.encrypt("Hello there, it appears that my name jeff")), false));
        byte [] hashA = h.auth(c.getCiphertext(), "mellamoefe".getBytes());
        byte [] hashB = h.auth(c.getCiphertext(), "mellamoefe".getBytes());
        StringBuilder sbA = new StringBuilder();
        for(byte foo : hashA){
            String hex = String.format("%02x", foo);
            sbA.append(hex);
        }

        StringBuilder sbB = new StringBuilder();
        for(byte foo : hashB){
            String hex = String.format("%02x", foo);
            sbB.append(hex);
        }
        System.out.println(sbA.toString());
        System.out.println(sbB.toString());
    }*/
    }
}
