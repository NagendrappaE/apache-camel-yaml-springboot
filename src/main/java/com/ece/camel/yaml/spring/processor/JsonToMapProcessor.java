package com.ece.camel.yaml.spring.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonToMapProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String jsonDataSourceString = exchange.getIn().getBody(String.class);

		TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
		};

		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(jsonDataSourceString, typeToken.getType());

		// Access the elements in the map
		Map<String, Object> data = (Map<String, Object>) map.get("data");
		List<Object> jacklist = (List<Object>) data.get("organizationalProcess");

		// System.out.println(jacklist);

		exchange.getIn().setBody(jacklist);

	}

}
