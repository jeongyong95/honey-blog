package com.joojeongyong.honey.blog.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils<T> {
	private static final ObjectMapper OBJECT_MAPPER = ObjectMapperFactory.defaultMapper();
	
	public static <T> T read(String json, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T read(String json, TypeReference<T> typeReference) {
		try {
			return OBJECT_MAPPER.readValue(json, typeReference);
		} catch (JsonProcessingException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T read(InputStream inputStream, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(inputStream, clazz);
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T read(InputStream inputStream, TypeReference<T> typeReference) {
		try {
			return OBJECT_MAPPER.readValue(inputStream, typeReference);
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
	
	public static <T> String write(T object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}
}
