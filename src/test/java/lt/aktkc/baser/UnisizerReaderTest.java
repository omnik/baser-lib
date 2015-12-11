package lt.aktkc.baser;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Date;


public class UnisizerReaderTest {

    @Test
    public void testMain() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("files/event.xml");
        BaserWriter writer = BaserWriter.buildFor(fileInputStream);

        Event2 event = new Event2();
        event.carId = 120;
        event.speed = 20;
        event.ign = false;
        event.lon = 54.2345;
        event.lat = 45.6673;
        event.locality = "Lithuania, Вильнюс";
        event.eventDatetime = new Date();
        event.altitude = 1015;
        event.b2v = true;

        byte[] write = writer.write(event);


        fileInputStream = new FileInputStream("files/event_read.xml");
        BaserReader<EventRead2> eventUnisizerReader = BaserReader.buildFor(fileInputStream, EventRead2.class);
        EventRead2 read = eventUnisizerReader.read(write);

        System.out.println(read.toString());

        Assert.assertEquals(event.carId, read.car_id);
        Assert.assertEquals(event.speed, read.speed);
        Assert.assertTrue((event.ign ? 1 : 0) == read.ign);
        Assert.assertEquals(event.locality, read.locality);
        Assert.assertEquals(event.eventDatetime, read.eventDatetime);


    }

    @Test
    public void testBits() throws Exception {

        byte b1 = (byte) (15);
        byte b2 = 15;
        byte b3 = (byte) ((b1) | (b2  << 4));

        printBytes((byte)(b1));
        System.out.println("+");
        printBytes(b2);
        System.out.println("=");
        printBytes(b3);
        System.out.println("");
        System.out.println("");

       // printBytes((byte)(b3));
        printBytes((byte)(b3&0x0F)); //same as b1
        printBytes((byte)(b3>>4&0x0f)); //same as b2
       // printBytes((byte)(b3>>4));

//        byte b4 = (byte) ((b3) | 0xF0);
//        byte b5 = (byte) (b3 & 0x0F);
//
//        printBytes(b4);
//        printBytes(b5);


    }

    private void printBytes(byte b1) {

        System.out.println(String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0'));
    }


}