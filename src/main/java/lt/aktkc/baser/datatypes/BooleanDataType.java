package lt.aktkc.baser.datatypes;


import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;

public class BooleanDataType extends DataType {
    public BooleanDataType(Integer id) {

        super(id, "boolean", 1);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Boolean) {
            bb.put(((Boolean) value) ? (byte) 1 : (byte) 0);
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to BooleanDataType");
        }
        return getBytesSize();
    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        byte value = source.get();
        Boolean valueInBool = false;
        if (value == 0x01) {
            valueInBool = true;
        }
        if (targetClass == null) {
            return null;
        }

        if (targetClass == boolean.class || targetClass == Boolean.class) {
            return valueInBool;
        } else if (targetClass == Integer.class || targetClass == int.class) {
            return valueInBool ? 1 : 0;
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from BooleanDataType");

        }
    }
}
