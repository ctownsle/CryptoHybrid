package RSA;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private Random r;
    private BigInteger q;
    private BigInteger p;
    private BigInteger N;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;
    private static final int bitlength = 1024;
    /*
        So far this program does calculations for finding a random p, q and n with bitlength of 1024
        I'll leave it at this for now
    */
    public RSA(final BigInteger e, final BigInteger N){
        this.e = e;
        this.N = N;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return N;
    }
}
