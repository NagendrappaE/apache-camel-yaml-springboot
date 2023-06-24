package com.ece.camel.yaml.spring.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonToMapProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String jsonDataSourceString = exchange.getIn().getBody(String.class);

		ObjectMapper objectMapper = new ObjectMapper();

		TypeReference<Map<String, Object>> mapType = new TypeReference<Map<String, Object>>() {
		};
		Map<String, Object> map = objectMapper.readValue(jsonDataSourceString, mapType);

		Object obj = map.get("data");

		String respo = objectMapper.writeValueAsString(obj);

		List<Map<String, Object>> jacklist = objectMapper.readValue(respo.toString(),
				new TypeReference<List<Map<String, Object>>>() {
				});

		System.out.println(jacklist);

		exchange.getIn().setBody(jacklist);

	}

}
