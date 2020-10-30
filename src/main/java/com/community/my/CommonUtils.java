package com.community.my;

import java.util.Random;

import javax.servlet.http.HttpSession;

import com.community.my.user.model.UserVO;

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
    
  //로그인 확인
  	public static UserVO getLoginUser(HttpSession hs) {
  		return (UserVO) hs.getAttribute(Const.LOGIN_USER);
  	}
  	
  //파싱
  	public static int parseInt(String str) {
  		int result = 0;
  		try {
  			result = Integer.parseInt(str);
  		}catch(Exception e) {
  		}
  		return result;
  	}
    

}