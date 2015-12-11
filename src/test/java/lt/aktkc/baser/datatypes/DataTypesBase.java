package lt.aktkc.baser.datatypes;

import lt.aktkc.baser.TypesClass;

import java.lang.reflect.Field;


public class DataTypesBase {

    static Class short1;
    static Class short2;
    static Class integer1;
    static Class integer2;
    static Class long1;
    static Class long2;
    static Class date;

    static {

        try {
            integer1 = TypesClass.class.getDeclaredField("integer1").getType();
            integer2 = TypesClass.class.getDeclaredField("integer2").getType();
            long1 = TypesClass.class.getDeclaredField("long1").getType();
            long2 = TypesClass.class.getDeclaredField("long2").getType();
            short1 = TypesClass.class.getDeclaredField("short1").getType();
            short2 = TypesClass.class.getDeclaredField("short2").getType();
            date = TypesClass.class.getDeclaredField("date").getType();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }



}
