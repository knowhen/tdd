package com.when.algorithm;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TemplateParserTest {

	TemplateParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new TemplateParser();
	}

	@Test
	public void emptyTemplateRendersAsEmptyString() {
		List<Segment> segments = parser.parseSegments("");
		assertSegments(segments, new PlainText(""));
	}

	@Test
	public void templateWithOnlyPlainText() {
		List<Segment> segments = parser.parseSegments("plain text only");
		assertSegments(segments, new PlainText("plain text only"));
	}
	
	@Test
	public void parsingMultipleVariables() {
		List<Segment> segments = parser.parseSegments("${a}:${b}:${c}");
		assertSegments(segments, new Variable("a"), new PlainText(":"), new Variable("b"), new PlainText(":"), new Variable("c"));
	}

	@Test
	public void parsingTemplateIntoSegmentObjects() {
		List<Segment> segments = parser.parseSegments("a ${b} c ${d}");
		assertSegments(segments, new PlainText("a "), new Variable("b"), new PlainText(" c "), new Variable("d"));
	}
	private void assertSegments(List<? extends Segment> actual, Segment... expected) {
		assertEquals("Number of segements doesn't match.", expected.length, actual.size());
		assertEquals(Arrays.asList(expected), actual);
	}

}
