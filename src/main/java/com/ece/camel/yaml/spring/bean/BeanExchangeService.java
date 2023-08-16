package com.ece.camel.yaml.spring.bean;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;

@Component
public class BeanExchangeService {
	
	
	public void setobject(Exchange exchange) {
		
		//exchange.getIn().setBody("NAGEEE");
		
		try {
		System.err.println("THE BEAN STREAM DATA"+exchange.getIn().getBody(String.class));

		Iterator<Message> exchangelist = exchange.getIn().getBody(Iterator.class);

		if (exchangelist != null && exchangelist.hasNext()) {

			Map<String, Object> row = (Map<String, Object>) exchangelist.next();

			Set<String> colums = row.keySet();

			System.out.println("the row" + row);
			System.out.println("the row" + colums);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

		
	}
	
	

}
