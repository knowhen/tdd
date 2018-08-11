package com.when.algorithm;

import java.util.Map;

public interface Segment {

	String evaluate(Map<String, String> variables);
	
	boolean isVariable();
	
	Variable asVariable();
	
	boolean isPlainText();
	
	PlainText asPlainText();
}
