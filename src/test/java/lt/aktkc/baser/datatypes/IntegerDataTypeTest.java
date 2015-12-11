package lt.aktkc.baser.datatypes;

import lt.aktkc.baser.TypesClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;


public class IntegerDataTypeTest extends DataTypesBase {
    @Test
    public void testWrite() throws Exception {
        IntegerDataType integerDataType = new IntegerDataType(1);
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        long valueLong = 1l;
        integerDataType.write(valueLong, byteBuffer);
        int intValue = 1;
        integerDataType.write(intValue, byteBuffer);

    }

    @Test
    public void testRead() throws Exception {
        ByteBuffer  bb = ByteBuffer.allocate(200);
        IntegerDataType integerDataType = new IntegerDataType(1);

        bb.putInt(15);
        bb.putInt(16);
        bb.putInt(17);
        bb.putInt(18);
        bb.flip();

        Object read1 = integerDataType.read(bb, integer1, 0);
        Object read2 = integerDataType.read(bb, integer2, 0);
        Object read3 = integerDataType.read(bb, long1, 0);
        Object read4 = integerDataType.read(bb, long2, 0);

        System.out.println(read1.getClass().getName()+" "+read1);
        System.out.println(read2.getClass().getName()+" "+read2);
        System.out.println(read3.getClass().getName()+" "+read3);
        System.out.println(read4.getClass().getName()+" "+read4);


    }
}