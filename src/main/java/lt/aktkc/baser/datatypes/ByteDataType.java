package lt.aktkc.baser.datatypes;

import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;


public class ByteDataType extends DataType {

    public ByteDataType(Integer id) {
        super(id, "byte", 1);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Byte) {
            bb.put((byte) value);
        } else if (value instanceof Integer) {
            bb.put(((Integer) value).byteValue());
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to ByteDataType");
        }
        return 1;

    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        Byte value = source.get();
        if (targetClass == null) {
            return null;
        }

        if (targetClass == Byte.class || targetClass == byte.class) {
            return value;
        } else if (targetClass == Integer.class) {
            return value.intValue();
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from ByteDataType");

        }
    }
}
