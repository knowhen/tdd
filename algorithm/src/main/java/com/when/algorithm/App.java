package com.when.algorithm;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Template template = new Template("${one}, ${two}, ${three}, ${four}, ${five}, 上山打老虎。\r\n" + "老虎没打着，打到小松鼠！\r\n"
				+ "门前大桥下，游过一群鸭。\r\n" + "快来快来数一数，\r\n" + "${two}, ${four}, ${six}, ${seven}, ${eight}。");
		template.set("one", "一");
		template.set("two", "二");
		template.set("three", "三");
		template.set("four", "四");
		template.set("five", "五");
		template.set("six", "六");
		template.set("seven", "七");
		template.set("eight", "八");
		System.out.println(template.evaluate());
	}
}
