package com.when.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TemplateTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();;
	private Template template;

	@Before
	public void setUp() {
		template = new Template("${one}, ${two}, ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");
	}

	@Test
	public void multipleVariables() throws Exception {
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void unkonwnVariablesAreIgnored() throws Exception {
		template.set("doesNotExist", "whatever");
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void testMissingValueRaisesExcepiton() throws Exception {
		exception.expect(RuntimeException.class);
		 exception.expectMessage("No value for ${foo}");
		 new Template("${foo}").evaluate();
	}
	
	@Ignore
	@Test
	public void getProcessedJustOnce() throws Exception {
		template.set("one", "${one}");
		template.set("two", "${three}");
		template.set("three", "${two}");
		assertTemplateEvaluatesTo("${one}, ${three}, ${two}");
	}

	private void assertTemplateEvaluatesTo(String expected) {
		assertEquals(expected, template.evaluate());
	}

}
