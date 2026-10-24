package com.github.diafter.jobqueue.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

/**
 * Centralized JSON serialization helper.
 */
@Component
@RequiredArgsConstructor
public class JsonSupport {

    private final ObjectMapper objectMapper;

    /**
     * Serializes a value to compact JSON.
     *
     * @param value value to serialize.
     * @return serialized JSON string.
     */
    public String write(final Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JacksonException exception) {
            throw new IllegalStateException("Failed to serialize JSON", exception);
        }
    }

    /**
     * Reads JSON as a typed value.
     *
     * @param json serialized JSON.
     * @param type target type.
     * @param <T> target type parameter.
     * @return parsed value.
     */
    public <T> T read(final String json, final Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JacksonException exception) {
            throw new IllegalStateException("Failed to deserialize JSON", exception);
        }
    }

    /**
     * Converts a JSON node into canonical compact JSON.
     *
     * @param node JSON node.
     * @return canonical JSON string.
     */
    public String canonical(final JsonNode node) {
        return write(node);
    }
}