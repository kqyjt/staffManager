package org.leafframework.util;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

	public static HashMap<String, Object> jsonToMap(String jsonStr)
			throws JsonParseException, JsonMappingException, IOException {

		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();

		jsonMap = mapper.readValue(jsonStr,
				new TypeReference<HashMap<String, String>>() {
				});
		return jsonMap;

	}
}
