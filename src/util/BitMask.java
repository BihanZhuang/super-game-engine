package util;

public class BitMask extends java.util.BitSet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public BitMask() {
        super();
    }
    
    public BitMask(int nbits) {
        super(nbits);
    }
    
    public BitMask(int...bitIndices) {
        super();
        set(bitIndices);
    }
    
    public void set(int...bitIndices) {
        for(int i: bitIndices) {
            set(i);
        }
    }

    public boolean isSuperset(BitMask other) {
        BitMask temp = (BitMask) clone();
        temp.and(other);
        return temp.equals(other);
    }
    
    public boolean isSubset(BitMask other) {
        return other.isSuperset(this);
    }
}
