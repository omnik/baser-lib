package lt.aktkc.baser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BaserWriter extends BaserBase {

    private static Logger logger = LoggerFactory.getLogger(BaserWriter.class);

    private int headerCapacity = 512;
    private int bodyCapacity = 10240;
    private List<Integer> flexFieldSize = new ArrayList<>();
    private ByteBuffer bbHeader;
    private ByteBuffer bbBody;
    private ByteBuffer bbTemp;


    private BaserWriter() {
    }

    public static BaserWriter buildFor(InputStream descriptionXml) {
        BaserWriter unisizerWriter = new BaserWriter();
        unisizerWriter.registerDefaults();
        unisizerWriter.parseDescriptionFile(descriptionXml);
        unisizerWriter.allocateCapacities(unisizerWriter.headerCapacity, unisizerWriter.bodyCapacity);


        return unisizerWriter;
    }

    public void allocateCapacities(int headerCapacity, int bodyCapacity) {
        this.headerCapacity = headerCapacity;
        this.bodyCapacity = bodyCapacity;
        bbHeader = ByteBuffer.allocate(headerCapacity);
        bbBody = ByteBuffer.allocate(bodyCapacity);
        bbTemp = ByteBuffer.allocate(bodyCapacity);
    }

    public byte[] write(Object instance) {
        bbHeader.limit(bbHeader.capacity()).position(0);
        bbBody.limit(bbHeader.capacity()).position(0);
        flexFieldSize.clear();

        bbHeader.put((byte) descriptionFile.fields.size());

        int fieldWritten = 0;

        for (DescriptionFileField field : descriptionFile.fields.values()) {

            Method getterMethod = null;
            Field declaredField = null;
            if (field.hasGetter()) {
                getterMethod = findGetter(field.getGetter(), instance.getClass());
            } else {
                declaredField = findField(field.name, instance.getClass());
            }

            if (getterMethod == null && declaredField == null) {
                logger.error("field '{}' not found in the object class hierarchy", field.name);
                continue;
            }


            Object value = null;

            try {
                if (getterMethod != null) {
                    value = getterMethod.invoke(instance);
                } else {
                    value = declaredField.get(instance);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error("", e);
            }


            DataType dataType = dataTypeByCommonName.get(field.type);
            if (dataType == null) {
                throw new RuntimeException("Can't find dataType with commonName '" + field.type + "'");
            }
            bbTemp.limit(bbTemp.capacity()).position(0);
            int written = dataType.write(value, bbTemp);
            if (written == -1) {
                continue;
            }


            bbHeader.put((byte) dataType.getDataTypeId().intValue());
            if (dataType.getBytesSize() == 0) {
                flexFieldSize.add(written);
            }

            bbBody.put((byte) field.id);
            bbTemp.flip();
            bbBody.put(bbTemp);

            fieldWritten++;

        }

        //after all types written - put flex field sizes
        for (Integer size : flexFieldSize) {
            bbHeader.putShort(size.shortValue());
        }

        bbHeader.flip();
        bbHeader.put((byte) fieldWritten);
        bbHeader.position(0);
        bbBody.flip();

        byte[] out = new byte[bbHeader.limit() + bbBody.limit()];
        bbHeader.get(out, 0, bbHeader.limit());
        bbBody.get(out, bbHeader.limit(), bbBody.limit());


        return out;

    }


}
