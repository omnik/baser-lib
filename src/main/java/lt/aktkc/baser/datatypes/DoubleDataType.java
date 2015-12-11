package lt.aktkc.baser.datatypes;

import lt.aktkc.baser.DataType;

import java.nio.ByteBuffer;


public class DoubleDataType extends DataType {
    public DoubleDataType(Integer id) {
        super(id, "double", 8);
    }

    @Override
    public int write(Object value, ByteBuffer bb) {
        if (value == null) {
            return -1;
        }
        if (value instanceof Double) {
            bb.putDouble((Double) value);
        } else {
            throw new RuntimeException("Can't write value of class " + value.getClass().getName() + " to DoubleDataType");
        }
        return 8;
    }

    @Override
    public Object read(ByteBuffer source, Class targetClass, Integer flexLen) {
        double value = source.getDouble();
        if (targetClass == null) {
            return null;
        }
        if (targetClass == Double.class) {
            return value;
        }else{
            throw new RuntimeException("Can't read value of class " + targetClass.getName() + " from DoubleDataType");

        }
    }
}
