package lt.aktkc.baser;

import lt.aktkc.baser.datatypes.*;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class BaserBase {

    protected Map<Integer, DataType> dataTypesById = new HashMap<>();
    protected Map<String, DataType> dataTypeByCommonName = new HashMap<>();

    protected DescriptionFileParser descriptionFileParser = new DescriptionFileParser();
    protected DescriptionFile descriptionFile;
    protected InstanceBuilder instanceBuilder = new DefaultInstanceBuilderImpl();

    public void registerType(DataType dataType) {
        if (dataTypesById.containsKey(dataType.getDataTypeId())) {
            throw new IllegalArgumentException("datatype with id " + dataType.getDataTypeId() + " already registered");
        }
        if (dataTypeByCommonName.containsKey(dataType.getCommonName())) {
            throw new IllegalArgumentException("datatype with common name " + dataType.getCommonName() + " already registered");
        }

        dataTypesById.put(dataType.getDataTypeId(), dataType);
        dataTypeByCommonName.put(dataType.getCommonName(), dataType);
    }

    public void setInstanceBuilder(InstanceBuilder instanceBuilder) {
        this.instanceBuilder = instanceBuilder;
    }

    protected void registerDefaults() {

        registerType(new BooleanDataType(1));

        registerType(new ByteDataType(2));
        registerType(new ShortDataType(3));
        registerType(new IntegerDataType(4));
        registerType(new LongDataType(5));

        registerType(new FloatDataType(6));
        registerType(new DoubleDataType(7));

        registerType(new StringUDataType(8));
        registerType(new ByteArrayDataType(9));

    }

    protected void parseDescriptionFile(InputStream descriptionXml) {
        descriptionFile = descriptionFileParser.parse(descriptionXml);
    }

    private Map<String, Field> cacheFields = new HashMap<>();
    private Map<String, Method> cacheSetter = new HashMap<>();
    private Map<String, Method> cacheGetter = new HashMap<>();

    protected Field findField(String name, Class clazz) {
        if (cacheFields.containsKey(name)) {
            return cacheFields.get(name);
        }
        Field declaredField = null;
        while (declaredField == null) {
            try {
                declaredField = clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
            }
            clazz = clazz.getSuperclass();
            if (clazz == Object.class || clazz == null) {
                break;
            }

        }
        cacheFields.put(name, declaredField);
        return declaredField;

    }

    protected Method findSetter(String setter, Class targetClass) {
        if (cacheSetter.containsKey(setter)) {
            return cacheSetter.get(setter);
        }
        Method setterMethod = null;
        while (setterMethod == null) {
            for (Method method : targetClass.getDeclaredMethods()) {
                if (method.getName().equals(setter) && method.getParameterCount() == 1) {
                    setterMethod = method;
                    break;
                }
            }
            targetClass = targetClass.getSuperclass();
            if (targetClass == Object.class || targetClass == null) {
                break;
            }
        }
        cacheSetter.put(setter, setterMethod);
        return setterMethod;

    }

    protected Method findGetter(String getter, Class targetClass) {
        if (cacheGetter.containsKey(getter)) {
            return cacheGetter.get(getter);
        }
        Method getterMethod = null;
        while (getterMethod == null) {
            for (Method method : targetClass.getDeclaredMethods()) {
                if (method.getName().equals(getter) && method.getReturnType() != Void.class) {
                    getterMethod = method;
                    break;
                }
            }
            targetClass = targetClass.getSuperclass();
            if (targetClass == Object.class || targetClass == null) {
                break;
            }
        }
        cacheGetter.put(getter, getterMethod);
        return getterMethod;

    }


}
