/**
 * 
 */
package com.ece.camel.yaml.spring.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * @author nagendrappae
 *
 */
@Component
public class UpperCaseProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		// let us capitalize the file name

		String receivedFileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);

		String upperCaseFileName = receivedFileName.toUpperCase();
		// Body
		Object obj = exchange.getIn().getBody();

		// setting back to exchange

		exchange.getIn().setHeader(Exchange.FILE_NAME, upperCaseFileName);

	}

}
