package com.when.algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("\\$\\{[^}]*\\}");
		Matcher matcher = pattern.matcher("${a}:${b}:${c}");
		while(matcher.find()) {
			System.out.println(matcher.group(0));
		}
	}
}
