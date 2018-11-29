import RSA.Alice;
import RSA.RSA;
import StoneTownsleyBlockCipher.BitwiseSimplifications;
import StoneTownsleyBlockCipher.Cipher;


public class Main {

    public static void main(String [] args){
        //TODO: stuff
        Alice a  = new Alice();

        RSA rsa = a.getRSAinstance();
        BitwiseSimplifications b = new BitwiseSimplifications();
        Cipher c = new Cipher();
        System.out.println(b.bytesToMessage(c.decrypt(c.encrypt("Hello there, it appears that my name jeff", rsa.getN(), rsa.getE()), rsa.getN(), rsa.getE()), false));
    }
}
