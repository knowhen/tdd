package com.when.algorithm;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PlainTextTest {

	@Test
	public void plainTextEvaluatesAsIs() {
		Map<String, String> variables = new HashMap<>();
		String text = "abc def";
		assertEquals(text, new PlainText(text).evaluate(variables));
	}

}
