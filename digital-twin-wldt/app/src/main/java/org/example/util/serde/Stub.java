package org.example.util.serde;

/**
 * Class responsible for managing serialization and deserialization of a type T
 * @param <T> type to serialize/deserialize
 */
public abstract class Stub<T> {
    private final Serializer<T> ser;
    private final Deserializer<T> de;

    public Stub(Serializer<T> ser, Deserializer<T> de) {
        this.ser = ser;
        this.de = de;
    }

    public String serialize(T t) {
        return ser.serializeToString(t);
    }

    public T deserialize(String src) {
        return de.deserializeFromString(src);
    }
}
