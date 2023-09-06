package com.ece.camel.yaml.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ece.camel.yaml.spring.configurer.HeaderConfigurer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HeaderConfigurerTest {

	@Mock
	private ObjectMapper objectMapper;

	@InjectMocks
	private HeaderConfigurer headerConfigurer;

	public HeaderConfigurerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGenericMapData() throws JsonMappingException, JsonProcessingException {
		// Mock data
        List<Object> linkedList = Arrays.asList("item1");
        
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        String jsonStringKey = "jsonKey";

       

        // Call the method you want to test
        List<Map<String, Object>> result = headerConfigurer.listOfMap(linkedList, data, jsonStringKey);

     

        // Verify the result
        assertEquals(linkedList.size(), result.size());

        // Verify that each map in the result contains the expected data
        for (Map<String, Object> map : result) {
            assertEquals("\"item1\"", map.get(jsonStringKey));
            // You can add more assertions for other keys/values in the map if needed
        }
	}
}
