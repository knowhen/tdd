package com.when.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
	private Map<String, String> variables;
	private String templateText;

	public Template(String templateText) {
		this.variables = new HashMap<>();
		this.templateText = templateText;
	}

	public void set(String variable, String value) {
		this.variables.put(variable, value);
	}

	public String evaluate() {
		TemplateParser parser = new TemplateParser();
		List<Segment> segments = parser.parseSegments(templateText);
		return concatenate(segments);
	}

	/**
	 * 将片段序列中的文本拼接起来
	 * 
	 * @param segments
	 *            文本片段序列
	 * @return
	 */
	private String concatenate(List<Segment> segments) {
		StringBuilder result = new StringBuilder();
		for (Segment segment : segments) {
			result.append(segment.evaluate(variables));
		}
		return result.toString();
	}
}
