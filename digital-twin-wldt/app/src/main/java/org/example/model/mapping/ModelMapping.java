package org.example.model.mapping;

public interface ModelMapping<T, M> {
    public M mapModel(T oldModel);
}
