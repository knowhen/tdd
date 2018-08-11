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

	@Override
	public boolean isVariable() {
		return true;
	}

	@Override
	public Variable asVariable() {
		return this;
	}

	@Override
	public boolean isPlainText() {
		return false;
	}

	@Override
	public PlainText asPlainText() {
		throw new RuntimeException("Can't turn an Variable to a PlainText");
	}

	@Override
	public String toString() {
		return "Variable [name=" + name + "]";
	}

}
