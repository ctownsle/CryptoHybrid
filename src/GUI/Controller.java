package GUI;

import RSA.Alice;
import RSA.Bob;

import java.math.BigInteger;

public class Controller implements Runnable {

    /**
     * See @Main
     */

    private MyScreen screen;
    private Alice a;
    private Bob b;

    public Controller(MyScreen screen){
        this.screen = screen;

    }

    @Override
    public void run() {
        screen.addToListModel("GENERATING ALICE PUBLIC KEY");
        a = new Alice();
        screen.addToListModel("ALICE PUBLIC ADDRESS KEY: " + "(" + new BigInteger(a.getRSAinstance().getN().toString(), 10).toString(16) + ", " +
                new BigInteger(a.getRSAinstance().getE().toString(), 10).toString(16) + ")");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        screen.addToListModel("GENERATING BOB PUBLIC KEY");
        b = new Bob();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        screen.addToListModel("BOB PUBLIC ADDRESS KEY: " + "(" + new BigInteger(b.getRSAinstance().getN().toString(), 10).toString(16) + ", " +
                new BigInteger(b.getRSAinstance().getE().toString(), 10).toString(16) + ")");
        screen.setA(a);
        screen.setB(b);
    }

}
