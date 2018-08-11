package com.when.algorithm;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class VariableTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	private Map<String, String> variables;
	
	@Before
	public void setUp() {
		variables = new HashMap<>();
	}
	
	@Test
	public void variableEvaluateToItsValue() {
		Map<String, String> variables = new HashMap<>();
		String name = "myVariable";
		String value = "myValue";
		variables.put(name, value);
		assertEquals(value, new Variable(name).evaluate(variables));
	}

	@Test
	public void missingVariableRaisesException() {
		String name = "myVariable";
		exception.expect(RuntimeException.class);
		exception.expectMessage("No value for ${" + name + "}");
		new Variable(name).evaluate(variables);
	}
}
