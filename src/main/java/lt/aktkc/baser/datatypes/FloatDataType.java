package lt.aktkc.baser.datatypes;


import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;

public class FloatDataType extends DataType {
    public FloatDataType(Integer id) {
        super(id, "float", 4);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Float) {
            bb.putFloat((Float) value);
        } else if (value instanceof Double) {
            bb.putFloat(((Double) value).floatValue());
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to FloatDataType");
        }
        return 8;
    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        float value = source.getFloat();
        if (targetClass == null) {
            return null;
        }

        if (targetClass == Float.class || targetClass == float.class) {
            return value;
        } else if (targetClass == Double.class || targetClass == double.class) {
            return (double) value;
        } else {
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from FloatDataType");

        }
    }
}
