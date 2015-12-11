package lt.aktkc.baser.datatypes;


import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;

public class ShortDataType extends DataType {

    public ShortDataType(Integer id) {
        super(id, "short", 2);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Short) {
            bb.putShort((Short) value);
        } else if (value instanceof Integer) {
            bb.putShort(((Integer)value).shortValue());
        } else if (value instanceof Long) {
            bb.putShort(((Long)value).shortValue());
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to ShortDataType");
        }
        return getBytesSize();
    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        short value = source.getShort();
        if (targetClass == null) {
            return null;
        }
        if (targetClass == Short.class || targetClass == short.class) {
            return value;
        } else if (targetClass == Integer.class || targetClass == int.class) {
            return (int) value;
        } else if (targetClass == Long.class || targetClass == long.class) {
            return (long) value;
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from ShortDataType");

        }
    }
}
