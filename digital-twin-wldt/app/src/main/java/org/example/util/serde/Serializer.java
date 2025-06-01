package org.example.util.serde;

/**
 * A Serializer is responsible to serialize a type into a string
 * @param <T> type to serialize
 */
@FunctionalInterface
public interface Serializer<T> {
    String serializeToString(T t);
}
