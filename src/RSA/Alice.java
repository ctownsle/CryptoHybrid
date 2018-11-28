package RSA;

import java.math.BigInteger;
import java.util.Random;

public class Alice {

    private Random r;
    private BigInteger p, q, N, phi, e;
    private static int bitlength = 1024;

    public Alice() {

        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        e = BigInteger.probablePrime(bitlength/ 2, r);
        N = p.multiply(q); // public
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        RSA rsa = new RSA(N, e);
    }
}
