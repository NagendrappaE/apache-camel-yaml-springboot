package com.ece.camel.yaml.spring.processor;

import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

@Component
public class JsonToMapProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		String jsonDataSourceString = exchange.getIn().getBody(String.class);
		System.out.println(jsonDataSourceString);

		Configuration configuration = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();


		Filter expensiveFilter = Filter.filter(Criteria.where("id").notEmpty());
		List<Map<String, Object>> expensive = JsonPath.parse(jsonDataSourceString,configuration).read("$['data'][?]",
				expensiveFilter);

		System.out.println(expensive);

		exchange.getIn().setBody(expensive);

	}

}
