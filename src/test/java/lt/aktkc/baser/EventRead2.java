package lt.aktkc.baser;

import java.util.BitSet;

public class EventRead2 extends EventRead {

    public int altitude;

    public boolean b1v = false;
    public boolean b2v = false;
    public boolean b3v = false;
    public boolean b4v = false;

    public void setBusenos(byte value){
        BitSet bitSet = BitSet.valueOf(new byte[]{value});
        b1v = bitSet.get(0);
        b2v = bitSet.get(1);
        b3v = bitSet.get(2);
        b4v = bitSet.get(3);
    }

    @Override
    public String toString() {
        return super.toString()+" EventRead2{" +
                "altitude=" + altitude +
                ", b1v=" + b1v +
                ", b2v=" + b2v +
                ", b3v=" + b3v +
                ", b4v=" + b4v +
                "} " ;
    }
}
