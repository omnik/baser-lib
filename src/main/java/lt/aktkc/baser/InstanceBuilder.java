package lt.aktkc.baser;


public interface InstanceBuilder<T> {
    T createInstance(Class clazz);
}
