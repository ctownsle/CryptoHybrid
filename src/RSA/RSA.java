package RSA;

import java.math.BigInteger;

public class RSA {

    /**
     * See @Main
     */

    private BigInteger N;
    private BigInteger e;

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
