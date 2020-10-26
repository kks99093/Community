package com.community.my;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) {
		long timeStamp = 1603380859620L;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
		System.out.println(sd);
		

	}

}
