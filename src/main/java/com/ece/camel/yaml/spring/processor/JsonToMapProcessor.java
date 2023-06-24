package com.ece.camel.yaml.spring.processor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

		
		/*
		 * Gson gson = new Gson();
		 * 
		 * Type mapType = new TypeToken<HashMap<String, Object>>() { }.getType();
		 * HashMap<String, Object> map = gson.fromJson(jsonDataSourceString, mapType);
		 * 
		 * List<HashMap<String, Object>> dataList = (List<HashMap<String, Object>>)
		 * map.get("data");
		 */
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		TypeReference<LinkedHashMap<String, Object>> mapType = new TypeReference<LinkedHashMap<String, Object>>() {};
		LinkedHashMap<String, Object> map = objectMapper.readValue(jsonDataSourceString, mapType);
		
		List<LinkedHashMap<String, Object>> jacklist=new ArrayList<>();
		
        List<Map.Entry<String, Object>> list = new ArrayList<>(map.entrySet());
        
        for (Map.Entry<String, Object> entry : list) {
        	
        	Map<String, Object> objMap = new LinkedHashMap<>();
        	objMap.put(entry.getKey(), entry.getValue());
            jacklist.add(map);
            
            
        	
        }

		

		
	//	List<Map<String, Object>> jacklist = objectMapper.readValue(jsonDataSourceString, new TypeReference<List<Map<String, Object>>>(){});

		
		
		/*
		 * Configuration configuration =
		 * Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
		 */
		
		/*
		 * Filter expensiveFilter = Filter.filter(Criteria.where("id").notEmpty());
		 * List<Map<String, Object>> expensive =
		 * JsonPath.parse(s2,configuration).read("$['data']", expensiveFilter);
		 */

		System.out.println(jacklist);

		exchange.getIn().setBody(jacklist);

	}

}
