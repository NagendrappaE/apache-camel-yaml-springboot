/**
 * 
 */
package com.ece.camel.yaml.spring.configurer;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

	String req = "[{\"URL\":\"http://localhost:8080/savestudent1\",\"REQBODY\":{\"URL\":\"3335101002388\",\"REQUESTBODY\":\"validateOTP\",\"reqParam1\":\"1234\",\"reqParam2\":\"256210\",\"reqType\":\"IB\",\"mandateRefNumber\":\"FDRL843vly\"},\"PARAMETERS\":\"\",\"SOURCE_TABLE_NM\":\"TA_TABLE1\"},{\"URL\":\"http://localhost:8080/savestudent2\",\"REQBODY\":{\"query\":\"{organization{id name process{id name}}}\"},\"PARAMETERS\":\"\",\"SOURCE_TABLE_NM\":\"TA_TABLE2\"}]";

	private final String CONFIG_TYPE_HEADER = "header";

	private final String CONFIG_TYPE_BODY = "body";

	private final String CONFIG_TYPE_PROPERTY = "property";

	private DocumentContext documentContext;

	private String file;

	public void process(String fileName, String match, String configType, String configLocation, Exchange exchange) {

		System.out.println("hiii" + fileName);

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

			Object lookupJsonData = documentContext.read("$." + match);

			if (lookupJsonData != null && configType.equals(CONFIG_TYPE_HEADER)) {
				exchange.getIn().setHeader(configLocation, lookupJsonData);

			} else if (lookupJsonData != null && configType.equals(CONFIG_TYPE_BODY)) {
				exchange.getIn().setBody(lookupJsonData);
			} else if (lookupJsonData != null && configType.equals(CONFIG_TYPE_PROPERTY)) {
				exchange.setProperty(fileName, lookupJsonData);
			}

		}

	}

	public String doSome(String data) {

		String replacedStr = data.replaceAll("'", "''");

		System.out.println("replaceddd" + replacedStr);

		return replacedStr;

	}

	public List<Map<String, Object>> listOfMapFromJsonString(String etlid, String etldts, String metadata,
			String connetnt) {

		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> hm = new LinkedHashMap<String, Object>();

		hm.put("ETL_ID", etlid);
		hm.put("ETL_TS", etldts);
		hm.put("METADATA", metadata);
		hm.put("CONTENT", connetnt);
		list.add(hm);

		return list;

	}

	public List<Map<String, Object>> GenericMap(Map<String, Object> data) {

		List<Map<String, Object>> list = new ArrayList<>();

		list.add(data);

		return list;

	}

	public String jsonStringSingleLine(Object inout) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.INDENT_OUTPUT);

		String singleLine = null;

		try {
			singleLine = mapper.writeValueAsString(inout);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return singleLine;

	}

	public List<Map<String, Object>> genericMapData(Map<String, Object> data, String jsonStringKey) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.INDENT_OUTPUT);

		List<Map<String, Object>> list = new ArrayList<>();

		data.computeIfPresent(jsonStringKey, (key, value) -> {

			String finalResp = null;
			try {
				finalResp = mapper.writeValueAsString(value);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return finalResp;

		});

		list.add(data);

		return list;

	}

	public List<Map<String, Object>> listOfMap(LinkedList<Object> linkedlist, String etlid, String etldts,
			String metadata, int batchSize) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.INDENT_OUTPUT);

		List<Map<String, Object>> list = new ArrayList<>();

		AtomicInteger counter = new AtomicInteger();

		Collection<List<Object>> li = linkedlist.parallelStream().map(item -> item)
				.collect(Collectors.groupingBy(x -> counter.getAndIncrement() / batchSize)).values();

		for (List<Object> l : li) {
			Map<String, Object> hm = new LinkedHashMap<String, Object>();

			hm.put("ETL_ID", etlid);
			hm.put("ETL_TS", etldts);
			hm.put("METADATA", metadata);
			try {
				hm.put("CONTENT", mapper.writeValueAsString(l));

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.add(hm);

		}
		System.out.println("the data " + linkedlist);
		System.out.println("the data " + li);

		return list;

	}

}
