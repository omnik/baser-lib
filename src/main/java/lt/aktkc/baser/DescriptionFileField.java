package lt.aktkc.baser;


import java.util.HashMap;
import java.util.Map;

public class DescriptionFileField {

    public int id;
    public String type;
    public String name;
    public String getter;
    public String setter;
    public Map<String, String> attributes = new HashMap<String, String>();


    public DescriptionFileField(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;

    }

    public void setSetter(String setter) {
        this.setter = setter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }


    public boolean hasSetter() {
        return setter != null && !setter.isEmpty();
    }

    public String getSetter() {
        return setter;
    }

    public boolean hasGetter() {
        return getter != null && !getter.isEmpty();
    }

    public String getGetter() {
        return getter;
    }
}
