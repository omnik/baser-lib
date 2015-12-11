package lt.aktkc.baser;


import java.util.BitSet;

public class Event2 extends Event {

    public Short altitude;
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

    public Byte getBusenos(){
        BitSet bitSet = BitSet.valueOf(new byte[]{0});
        bitSet.set(0, b1v);
        bitSet.set(1, b2v);
        bitSet.set(2, b3v);
        bitSet.set(3, b4v);
        byte[] bytes = bitSet.toByteArray();
        if (bytes.length == 0) {
            return null;
        }
        return bytes[0];
    }


}
