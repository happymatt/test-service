package com.jhqc.pxsj.config;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertySource {

	private String name;

	private Map<?, ?> source;

	@JsonCreator
	public PropertySource(@JsonProperty("name") String name,
			@JsonProperty("source") Map<?, ?> source) {
		this.name = name;
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public Map<?, ?> getSource() {
		return source;
	}

	@Override
	public String toString() {
		return "PropertySource [name=" + name + "]";
	}

}
