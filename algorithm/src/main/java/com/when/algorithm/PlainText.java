package com.when.algorithm;

import java.util.Map;

public class PlainText implements Segment {

	private String text;

	public PlainText(String text) {
		this.text = text;
	}

	@Override
	public String evaluate(Map<String, String> variables) {
		 return text;
	}

	@Override
	public boolean equals(Object other) {
		return text.equals(((PlainText) other).text);
	}

	@Override
	public boolean isVariable() {
		return false;
	}

	@Override
	public Variable asVariable() {
		throw new RuntimeException("Can't turn a PlainText to an Variable");
	}

	@Override
	public boolean isPlainText() {
		return true;
	}

	@Override
	public PlainText asPlainText() {
		return this;
	}

	@Override
	public String toString() {
		return "PlainText [text=" + text + "]";
	}
	
}
