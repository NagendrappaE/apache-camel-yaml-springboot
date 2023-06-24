package com.ece.camel.yaml.spring.processor;

import java.lang.reflect.Type;
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

		
		Gson gson = new Gson();

		Type mapType = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> map = gson.fromJson(jsonDataSourceString, mapType);

		List<Map<String, Object>> dataList = (List<Map<String, Object>>) map.get("data");

		/*
		 * Configuration configuration =
		 * Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
		 */
		
		/*
		 * Filter expensiveFilter = Filter.filter(Criteria.where("id").notEmpty());
		 * List<Map<String, Object>> expensive =
		 * JsonPath.parse(s2,configuration).read("$['data']", expensiveFilter);
		 */

		System.out.println(dataList);

		exchange.getIn().setBody(dataList);

	}

}
