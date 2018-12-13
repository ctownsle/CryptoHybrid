package GUI;

import StoneTownsleyBlockCipher.BitwiseSimplifications;
import StoneTownsleyBlockCipher.Cipher;

import java.math.BigInteger;

public class EncryptionDecryptionController implements Runnable {
    /**
     * See @Main
     */
    private MyScreen screen;
    private BigInteger E, N;
    private String message;
    private boolean flag;
    private BitwiseSimplifications b;

    public EncryptionDecryptionController(final MyScreen screen, final BigInteger N, final BigInteger E, final String message, final boolean flag){
        this.screen = screen;
        this.N = N;
        this.E = E;
        this.message = message;
        this.flag = flag;
        b = new BitwiseSimplifications();
    }
    @Override
    public void run() {
        try {
            Cipher c = new Cipher(N, E, screen);
            byte [] cipherText = c.encrypt(message);
            byte [] decryptedText = c.decrypt(cipherText);
            if(decryptedText == null){
                screen.addToListModel("MESSAGE NOT VERIFIED");
            } else {
                if (flag){
                    screen.addToBobListModel("[ALICE]: " + b.bytesToMessage(decryptedText, true));
                } else {
                    screen.addToAliceListModel("[BOB]: " + b.bytesToMessage(decryptedText, true));
                }
            }

        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
