/**
 * 
 */
package com.ece.camel.yaml.spring.configurer;

import java.io.FileInputStream;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

/**
 * @author nagendrappae
 *
 */

@Component
public class HeaderConfigurer {

	private final String CONFIG_TYPE_HEADER = "header";

	private final String CONFIG_TYPE_BODY = "body";

	private final String CONFIG_TYPE_PROPERTY = "property";

	private DocumentContext documentContext;

	private String file;

	public void process(String fileName, String match, String configType, String configLocation, Exchange exchange) {

		System.out.println("hiii"+fileName);
		
		if (StringUtils.hasText(fileName)) {

			try {

				if (!fileName.equals(file)) {

					try (FileInputStream inputStream = new FileInputStream(ResourceUtils.getFile(fileName))) {

						Configuration configuration = Configuration.defaultConfiguration()
								.addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
						documentContext = JsonPath.using(configuration).parse(inputStream);
						file = fileName;
					}

				}

			} catch (Exception e) {
				e.printStackTrace();

			}

			Object lookupJsonData = documentContext.read("$" + match);

			if (lookupJsonData != null && configType.equals(CONFIG_TYPE_HEADER)) {
				exchange.getIn().setHeader(configLocation, lookupJsonData);

			} else if (lookupJsonData != null && configType.equals(CONFIG_TYPE_BODY)) {
				exchange.getIn().setBody(lookupJsonData);
			} else if (lookupJsonData != null && configType.equals(CONFIG_TYPE_PROPERTY)) {
				exchange.setProperty(fileName, lookupJsonData);
			}

		}

	}

}
