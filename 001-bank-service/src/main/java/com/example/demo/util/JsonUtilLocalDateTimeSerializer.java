package com.example.demo.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author Rajkumar Banala 14-May-2019
 *
 * 
 */

public class JsonUtilLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtilLocalDateTimeSerializer.class);

	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	/**
	 * Used JsonSerializer<LocalDateTime> & JsonDeserializer<LocalDateTime> instead of @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 * Because Serialization & De-Serialization is not working with @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	 */
	@Override
	public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
		LOG.debug("serialize()");

		try {

			String formattedDate = date.format(dateTimeFormatter);
			LOG.debug("serialize().formattedDate:" + formattedDate);

			jsonGenerator.writeString(formattedDate);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
