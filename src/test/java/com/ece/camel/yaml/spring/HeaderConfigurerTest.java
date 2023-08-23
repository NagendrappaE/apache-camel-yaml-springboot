package com.ece.camel.yaml.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ece.camel.yaml.spring.configurer.HeaderConfigurer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HeaderConfigurerTest {
	
	private HeaderConfigurer headerConfigurer;
	
	@BeforeEach
    public void setUp() {
		headerConfigurer = new HeaderConfigurer(); 
    }

	@Test
    public void testGenericMapData() throws JsonMappingException, JsonProcessingException {
        // Create a sample data map
        
        String jsonStr="{\"ETL_ID\":\"John\",\"ETL_TS\":\"12-02-2023\",\"CONTENT\":{\"name\":\"abc\"}}";
        Map<String, Object> data = new ObjectMapper().readValue(jsonStr, LinkedHashMap.class);


        // Specify the jsonStringKey
        String jsonStringKey = "CONTENT";

        // Call the genericMapData method
        List<Map<String, Object>> resultList = headerConfigurer.genericMapData(data, jsonStringKey);

        // Verify the results
        assertEquals(1, resultList.size());

        Map<String, Object> resultMap = resultList.get(0);
        assertTrue(resultMap.containsKey(jsonStringKey));

        Object value = resultMap.get(jsonStringKey);
        assertTrue(value instanceof String);

        try {
            
            // Assert that the deserialized map is equal to the original value
            assertEquals(data.get(jsonStringKey), resultList.get(0).get(jsonStringKey));
        } catch (Exception e) {
            // If deserialization fails, fail the test
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
