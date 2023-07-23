package com.ece.camel.yaml.spring.processor;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
        HttpServletRequest request = exchange.getIn().getBody(HttpServletRequest.class);

        
        System.out.println("hhh"+request.getHeader("x-hopex-session-token"));
		
	}

}
