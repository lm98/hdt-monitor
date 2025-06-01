package org.example.util.serde;

/**
 * A deserializer is responsible to extract a value of type T from a string
 * @param <T> type to deserialize
 */
@FunctionalInterface
public interface Deserializer<T> {
    T deserializeFromString(String src);
}
