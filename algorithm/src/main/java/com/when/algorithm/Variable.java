package com.when.algorithm;

import java.util.Map;

public class Variable implements Segment {

	private String name;

	public Variable(String name) {
		this.name = name;
	}

	@Override
	public String evaluate(Map<String, String> variables) {
		if(!variables.containsKey(name)) {
			throw new RuntimeException("No value for ${" + name + "}");
		}
		return variables.get(name);
	}

	@Override
	public boolean equals(Object other) {
		return name.equals(((Variable) other).name);
	}

}
