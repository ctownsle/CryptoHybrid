import GUI.MyScreen;


/**
 * @RSA holds a public N and e to generate a public address for @Alice and @Bob
 * @HMAC Uses the algorithm described in RFC 2104 to generate a boolean which determines whether or not the message
 * is verified or not
 * @Cipher handles symmetric encryption, decryption and HMAC authentication
 * @Alice essentially just holds a public address generated from @RSA
 * @Bob essentially just holds a public address generated from @RSA
 * @MyScreen creates a screen that passes messages between two clients. Creates
 * threads to handle any and all calculations by passing information to either @EncryptionDecryptionController
 * to handle encryption and decryption calculations or @Controller to generate public addresses on startup.
 */

public class Main {

    public static void main(String [] args) {
        MyScreen jeff = new MyScreen();

    }
}
