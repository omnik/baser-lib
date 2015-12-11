package lt.aktkc.baser.datatypes;


import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;

public class IntegerDataType extends DataType {
    public IntegerDataType(Integer id) {
        super(id, "int", 4);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Integer) {
            bb.putInt((Integer) value);
        } else if (value instanceof Long) {
            bb.putInt(((Long)value).intValue());
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to IntegerDataType");
        }
        return getBytesSize();

    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        int anInt = source.getInt();
        if (targetClass == null) {
            return null;
        }
        if (targetClass == Integer.class || targetClass == int.class) {
            return anInt;
        } else if (targetClass == Long.class || targetClass == long.class) {
            return (long) anInt;
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from IntegerDataType");

        }


    }
}
