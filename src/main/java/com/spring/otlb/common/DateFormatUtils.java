package com.otlb.semi.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateFormatUtils {
	public static String formatDate(Timestamp date) {
	
		if(date != null) {
			SimpleDateFormat formatedDate = new SimpleDateFormat("yy-MM-dd [HH:mm]");
			String result = formatedDate.format(date);
			
			return result;
		} else {
			return null;
		}
	}
	public static String formatDateBoard(Timestamp date) {
		
		if(date != null) {
			SimpleDateFormat tsdate = new SimpleDateFormat("yyyy-MM-dd");
			String str = tsdate.format(date);
			LocalDate localDate = LocalDate.now();
//			System.out.println(str);
//			System.out.println(localDate);
			//게시글의 작성일자가 오늘날짜와 같으면 시간만 출력
			if(str.equals(localDate.toString())) {
				SimpleDateFormat time = new SimpleDateFormat("HH:mm");
				String result = time.format(date);
				return result;
			}
			SimpleDateFormat formatedDate = new SimpleDateFormat("yy-MM-dd [HH:mm]");
			String result = formatedDate.format(date);
			
			return result;
		} else {
			return null;
		}
	}
}
