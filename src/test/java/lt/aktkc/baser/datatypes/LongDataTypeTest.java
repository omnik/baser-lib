package lt.aktkc.baser.datatypes;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Date;

import static org.junit.Assert.*;

public class LongDataTypeTest extends DataTypesBase {

    @Test
    public void testWrite() throws Exception {
        Date date = new Date();
        long value = 15;
        int intVal = 15;

        LongDataType longDataType = new LongDataType(1);
        longDataType.write(date, ByteBuffer.allocate(20));
        longDataType.write(value, ByteBuffer.allocate(20));
        longDataType.write(intVal, ByteBuffer.allocate(20));


    }

    @Test
    public void testRead() throws Exception {
        ByteBuffer bb = ByteBuffer.allocate(200);
       LongDataType longDataType = new LongDataType(1);

        bb.putLong(15);
        bb.putLong(16);
        bb.putLong(17);
        bb.putLong(18);
        bb.putLong(new Date().getTime());

        bb.flip();

        Object read1 = longDataType.read(bb, integer1, 0);
        Object read2 = longDataType.read(bb, integer2, 0);
        Object read3 = longDataType.read(bb, long1, 0);
        Object read4 = longDataType.read(bb, long2, 0);
        Object read5 = longDataType.read(bb, date, 0);

        System.out.println(read1.getClass().getName()+" "+read1);
        System.out.println(read2.getClass().getName()+" "+read2);
        System.out.println(read3.getClass().getName()+" "+read3);
        System.out.println(read4.getClass().getName()+" "+read4);
        System.out.println(read5.getClass().getName()+" "+read5);

    }
}