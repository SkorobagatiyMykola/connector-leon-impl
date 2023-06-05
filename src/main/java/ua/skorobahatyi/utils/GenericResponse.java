package ua.skorobahatyi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericResponse<T> {
    private T entity;

    public GenericResponse(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public static <T> GenericResponse<T> createInstance(String originalResponse, Class<T> valueType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T response = mapper.readValue(originalResponse, valueType);

        return new GenericResponse<T>(response);
    }

    public static <T> GenericResponse<T> createInstance(String originalResponse, TypeReference<T> typeReference) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        T response = mapper.readValue(originalResponse, typeReference);

        return new GenericResponse<T>(response);
    }
}
