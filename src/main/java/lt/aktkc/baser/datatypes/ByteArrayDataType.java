package lt.aktkc.baser.datatypes;


import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;

public class ByteArrayDataType extends DataType {

    public ByteArrayDataType(Integer dataTypeId) {
        super(dataTypeId, "bytea", 0);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof byte[]) {
            byte[] data = (byte[]) value;
            bb.put(data);
            return data.length;
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to ByteArrayDataType");
        }

    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {

        byte[] valueBytes = new byte[flexLen];
        source.get(valueBytes);

        if (targetClass == null) {
            return null;
        }
        if (targetClass == byte[].class) {
            return valueBytes;
        }  else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from ByteArrayDataType");
        }
    }
}
