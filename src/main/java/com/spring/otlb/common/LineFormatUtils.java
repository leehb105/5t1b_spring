package com.otlb.semi.common;

public class LineFormatUtils {
	public static String formatLine(String str) {
		//XSS 공격대시 <> 변환처리
		String content = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		//개행문자처리
		content = content.replaceAll("\n", "<br/>");
		return content;
	}
}
