package StoneTownsleyBlockCipher;

import java.util.ArrayList;
import java.util.Arrays;

public class BitwiseSimplifications {

    static final int BITSIZE = Byte.SIZE;
    /*
    n is the number being rotated and d is the the number of bits to rotate by
     */
    public BitwiseSimplifications(){}

    public byte leftRotate(byte n, int d){
        int m1 =  n & ( ((1 << d) - 1) << (BITSIZE - d));
        int m2 = n & (1 << (BITSIZE - d)) - 1;
        return (byte) (m2 << d | m1 >> (BITSIZE - d));
    }

    public byte rightRotate(byte n, int d){
        int m1 = n & ((1 << d) - 1);
        int m2 = n & ((1 << BITSIZE - (d)) - 1) << d;
        return (byte) ((m1 << BITSIZE - d)| (m2 >> d));
    }

    public ArrayList<byte []> splitBytes(final byte[] bytes){
        ArrayList<byte []> byterinos = new ArrayList<>();
        if(bytes.length % 8 == 0) {
            for (int i = 1; i <= bytes.length / 8; i++) {
                byte[] temp = Arrays.copyOfRange(bytes, ((i * 8) - 8), i * 8);
                byterinos.add(temp);
            }
        } else {
            for (int i = 1; i <= bytes.length/ 8 + 1; i ++) {
                byte [] temp = Arrays.copyOfRange(bytes, ((i * 8) - 8), i * 8);
                byterinos.add(temp);
            }
        }
        return byterinos;
    }
}
