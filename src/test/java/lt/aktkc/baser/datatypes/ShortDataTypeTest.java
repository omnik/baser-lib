package lt.aktkc.baser.datatypes;

import lt.aktkc.baser.TypesClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;


public class ShortDataTypeTest extends DataTypesBase {

    @Test
    public void testWrite() throws Exception {

        ShortDataType shortDataType = new ShortDataType(1);

        short shortValue = 12;
        shortDataType.write(shortValue, ByteBuffer.allocate(10));
        shortDataType.write(new Integer(12), ByteBuffer.allocate(10));
        shortDataType.write(new Long(12l), ByteBuffer.allocate(10));


    }

    @Test
    public void testRead() throws Exception {
        ShortDataType shortDataType = new ShortDataType(1);
        ByteBuffer bb = ByteBuffer.allocate(40);
        bb.putShort((short) 20);
        bb.putShort((short) 20);
        bb.putShort((short) 30);
        bb.putShort((short) 30);
        bb.putShort((short) 40);
        bb.putShort((short) 40);

        bb.flip();

        Object read1 = shortDataType.read(bb, short1, 0);
        Object read2 = shortDataType.read(bb, short2, 0);
        Object read3 = shortDataType.read(bb, integer1, 0);
        Object read4 = shortDataType.read(bb, integer2, 0);
        Object read5 = shortDataType.read(bb, long1, 0);
        Object read6 = shortDataType.read(bb, long2, 0);

        System.out.println(read1.getClass().getName()+" "+read1);
        System.out.println(read2.getClass().getName()+" "+read2);
        System.out.println(read3.getClass().getName()+" "+read3);
        System.out.println(read4.getClass().getName()+" "+read4);
        System.out.println(read5.getClass().getName()+" "+read5);
        System.out.println(read6.getClass().getName()+" "+read6);


    }
}