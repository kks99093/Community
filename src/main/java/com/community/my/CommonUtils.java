package com.community.my;

import java.util.Random;

public class CommonUtils {
	//인증키 사이즈
	private static int size = 6;
	
	
	//이메일 인증키 생성
    public static String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();   
    }
    

}