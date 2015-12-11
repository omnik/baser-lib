package lt.aktkc.baser.datatypes;


import lt.aktkc.baser.DataType;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class StringUDataType extends DataType {
    public StringUDataType(Integer id) {
        super(id, "stringU", 0);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }

        if (value instanceof String) {
            String v = (String) value;
            try {
                byte[] bytes = v.getBytes("UTF-8");
                bb.put(bytes);
                return bytes.length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        } else if (value instanceof byte[]) {
            byte[] value1 = (byte[]) value;
            bb.put(value1);
            return value1.length;
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to StringUDataType");
        }

    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        byte[] valueBytes = new byte[flexLen];
        source.get(valueBytes);

        if (targetClass == null) {
            return null;
        }
        if (targetClass == String.class) {
            try {
                return new String(valueBytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else if (targetClass == byte[].class) {
            return valueBytes;
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from StringUDataType");
        }


    }
}
