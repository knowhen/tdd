package com.when.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 模板解析器。
 * 将模板文本解析成片段序列。
 * @author when
 *
 */
public class TemplateParser {

	public List<Segment> parseSegments(String template) {
		List<Segment> segments = new ArrayList<>();
		List<String> strings = parse(template);
		for(String s : strings) {
			if(isVariable(s)) {
				String name = s.substring(2, s.length() - 1);
				segments.add(new Variable(name));
			} else {
				segments.add(new PlainText(s));
			}
		}
		return segments;
	}
	
	private List<String> parse(String template) {
		List<String> segments = new ArrayList<>();
		int index = collectSegments(segments, template);
		addTail(segments, template, index);
		addEmptyStringIfTemplateWasEmpty(segments);
		return segments;
	}

	/**
	 * 
	 * @param segments
	 * @param src
	 * @return
	 */
	private int collectSegments(List<String> segments, String src) {
		// 匹配${variable}
		Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
		Matcher matcher = pattern.matcher(src);
		// 标记匹配到的变量尾部下标
		int index = 0;
		while (matcher.find()) {
			addPrecedingPlainText(segments, src, matcher, index);
			addVariable(segments, src, matcher);
			index = matcher.end();
		}
		return index;
	}

	// 添加纯文本部分
	private void addPrecedingPlainText(List<String> segments, String src, Matcher matcher, int index) {
		if (index != matcher.start()) {
			segments.add(src.substring(index, matcher.start()));
		}
	}

	// 添加变量
	private void addVariable(List<String> segments, String src, Matcher matcher) {
		segments.add(src.substring(matcher.start(), matcher.end()));
	}

	// 如果结尾部分还有纯文本，附加
	private void addTail(List<String> segments, String template, int index) {
		if (index < template.length()) {
			segments.add(template.substring(index));
		}
	}

	//模板为空时添加空字符串
	private void addEmptyStringIfTemplateWasEmpty(List<String> segments) {
		if (segments.isEmpty()) {
			segments.add("");
		}
	}
	
	/**
	 * 判断该文本片段是否为需要赋值的变量
	 * 
	 * @param segment
	 *            文本片段
	 * @return
	 */
	private boolean isVariable(String segment) {
		return segment.startsWith("${") && segment.endsWith("}");
	}
}
