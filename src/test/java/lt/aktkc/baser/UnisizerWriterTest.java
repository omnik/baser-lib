package lt.aktkc.baser;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.Calendar;


public class UnisizerWriterTest {

    @Test
    public void testMain() throws Exception {

        FileInputStream fileInputStream = new FileInputStream("files/event.xml");

        BaserWriter unisizerWriter = BaserWriter.buildFor(fileInputStream);

        Event2 event = new Event2();
        event.carId = 255;
        event.lon = 25.342125;
        event.lat = 54.112234;
        event.eventDatetime = Calendar.getInstance().getTime();
        event.ign = false;
        event.speed = 40;
        event.locality = "Lithuania, Vilnius";

        long t = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            byte[] bytes2 = unisizerWriter.write(event);
//        }


        System.out.println("in " + (System.currentTimeMillis() - t));
        byte[] bytes = unisizerWriter.write(event);
        System.out.println("len=" + bytes.length);
        System.out.println(bytesToHex(bytes));


    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}

