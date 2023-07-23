/**
 * 
 */
package com.ece.camel.yaml.spring.events;

import org.apache.camel.spi.CamelEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.springframework.stereotype.Component;

/**
 * @author nagendrappae
 *
 */
@Component
public class CamelEvents extends EventNotifierSupport {

	@Override
	public void notify(CamelEvent event) throws Exception {

		System.err.println("EVENT::::::::" + event);

	}

	/*
	 * public boolean isEnabled(EventObject event) {
	 * 
	 * return true;
	 * 
	 * }
	 */

}
