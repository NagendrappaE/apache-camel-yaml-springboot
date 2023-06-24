/**
 * 
 */
package com.ece.camel.yaml.spring.processor;

import java.util.Iterator;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * @author nagendrappae
 *
 */
@Component
public class SnowFlakeProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		try {
			System.err.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");

			Iterator<Message> exchangelist = exchange.getIn().getBody(Iterator.class);

			if (exchangelist != null && exchangelist.hasNext()) {

				Map<String, Object> row = (Map<String, Object>) exchangelist.next();

				System.out.println("the row" + row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
