package lt.aktkc.baser.datatypes;

import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;
import java.util.Date;


public class LongDataType extends DataType {

    public LongDataType(Integer id) {
        super(id, "long", 8);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Long) {
            bb.putLong((Long) value);
        } else if (value instanceof Date) {
            bb.putLong(((Date) value).getTime());
        } else if (value instanceof Integer) {
            bb.putLong(((Integer) value).longValue());
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to LongDataType");
        }
        return getBytesSize();

    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        long value = source.getLong();
        if (targetClass == null) {
            return null;
        }
        if (targetClass == Long.class || targetClass == long.class) {
            return value;
        } else if (targetClass == Date.class) {
            return new Date(value);
        } else if (targetClass == Integer.class || targetClass == int.class) {
            return (int) value;
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from LongDataType");

        }
    }
}
