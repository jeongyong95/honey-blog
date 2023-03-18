package com.joojeongyong.honey.blog.domain.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectMapperFactory {
	public static ObjectMapper defaultMapper() {
		var objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		return objectMapper;
	}
	
	public static ObjectMapper mapperForApi() {
		return defaultMapper()
			.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
	}
}
