package lt.aktkc.baser;


import java.nio.ByteBuffer;

public abstract class DataType {

    private Integer dataTypeId;
    private String commonName;
    private Integer bytesSize;

    public DataType(Integer dataTypeId, String commonName, Integer bytesSize) {
        this.dataTypeId = dataTypeId;
        this.commonName = commonName;
        this.bytesSize = bytesSize;
    }

    public Integer getBytesSize() {
        return bytesSize;
    }

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public String getCommonName() {
        return commonName;
    }

    public abstract int write(Object value, ByteBuffer bb);

    public abstract Object read(ByteBuffer source, Class targetClass, Integer flexLen);


    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }
}
