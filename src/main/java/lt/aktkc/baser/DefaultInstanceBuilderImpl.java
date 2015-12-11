package lt.aktkc.baser;


public class DefaultInstanceBuilderImpl implements InstanceBuilder {

    @Override
    public Object createInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
