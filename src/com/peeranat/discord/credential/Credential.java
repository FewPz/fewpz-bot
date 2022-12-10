package com.peeranat.discord.credential;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class Credential {

	private List<String> _content;
	private ConcurrentHashMap<String, Object> values;
	
	public Credential() {
		values = new ConcurrentHashMap<>();
		try {
			_content = Files.readAllLines(Paths.get("credentials.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String txt : _content) {
			int index = txt.indexOf('=');
			if(index != -1) {
				String key = txt.substring(0, index).trim();
				String value = txt.substring(index+1).trim();
				values.put(key, value);
			}
		}
	}
	
	public String asString(String key) {
		return values.get(key).toString();
	}
	
	public int asInt(String key) {
		return Integer.parseInt(asString(key));
	}
	
	public double asDouble(String key) {
		return Double.parseDouble(asString(key));
	}

	public double asFloat(String key) {
		return Float.parseFloat(asString(key));
	}
	
	public boolean asBoolean(String key) {
		return Boolean.parseBoolean(asString(key));
	}
	
	public long asLong(String key) {
		return Long.parseLong(asString(key));
	}
	
	public void debug() {
		System.out.println("Get All key and value in credential");
		for (Entry<String, Object> _value : values.entrySet()) {
			System.out.println(_value.getKey() + " : " + _value.getValue());
		}
	}
	
}
