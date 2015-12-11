package lt.aktkc.baser;


import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BaserReader<T> extends BaserBase {

    private Class<T> targetClass;

    private BaserReader() {
    }

    public static <T> BaserReader<T> buildFor(InputStream metaXml, Class<T> targetClass) {

        BaserReader<T> reader = new BaserReader<>();
        reader.registerDefaults();
        reader.parseDescriptionFile(metaXml);
        reader.targetClass = targetClass;

        return reader;
    }

    class FieldMeta {
        public FieldMeta(DataType dataType) {
            this.dataType = dataType;
        }

        DataType dataType;
        int size;
    }

    public T read(byte[] data) {

        ByteBuffer wrap = ByteBuffer.wrap(data);
        int fieldsSize = (int) wrap.get();

        List<FieldMeta> types = new ArrayList<>(fieldsSize);
        List<Integer> flexFieldsIndex = new ArrayList<>();

        for (int i = 0; i < fieldsSize; i++) {
            int type = (int) wrap.get();
            DataType dataType = dataTypesById.get(type);
            if (dataType == null) {
                throw new RuntimeException("Can't find dataType with id " + type);
            }
            types.add(new FieldMeta(dataType));
            if (dataType.getBytesSize() == 0) {
                flexFieldsIndex.add(i);
            }
        }
        for (int i = 0; i < flexFieldsIndex.size(); i++) {
            Integer fieldIndex = flexFieldsIndex.get(i);
            short fieldSize = wrap.getShort();
            types.get(fieldIndex).size = fieldSize;

        }


        T t = (T) instanceBuilder.createInstance(targetClass);

        for (FieldMeta type : types) {

            Integer fieldId = (int) wrap.get();

            DescriptionFileField descriptionFileField = descriptionFile.fields.get(fieldId);
            if (descriptionFileField == null) {
                //v byte[] field exist, but we don't have info about that it is.
                //need to skip data
                Integer bytesSize = type.dataType.getBytesSize();
                if (bytesSize == 0) {
                    bytesSize = type.size;
                }
                wrap.position(wrap.position() + bytesSize);
                continue;
            }
            DataType dataTypeInBB = dataTypeByCommonName.get(descriptionFileField.type);
            Class targetType = null;
            Method setter = null;
            Field declaredField = null;

            if (descriptionFileField.hasSetter()) {
                setter = findSetter(descriptionFileField.getSetter(), targetClass);
                if (setter != null) {
                    targetType = setter.getParameterTypes()[0];
                }
            } else {
                declaredField = findField(descriptionFileField.name, targetClass);
                if (declaredField != null) {
                    targetType = declaredField.getType();
                }
            }

            Object value = dataTypeInBB.read(wrap, targetType, type.size);
            try {
                if (setter != null) {
                    setter.invoke(t, value);
                } else if (declaredField != null) {
                    declaredField.set(t, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return t;


    }


}
