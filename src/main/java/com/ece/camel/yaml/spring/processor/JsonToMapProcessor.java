package com.ece.camel.yaml.spring.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;

@Component
public class JsonToMapProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String jsonDataSourceString = exchange.getIn().getBody(String.class);

		Filter expensiveFilter = Filter.filter(Criteria.where("id").notEmpty());
		List<Map<String, Object>> expensive = JsonPath.parse(jsonDataSourceString).read("$['data'][?]",
				expensiveFilter);

		System.out.println(expensive);

		exchange.getIn().setBody(expensive);

	}

}
