package com.community.my;

import javax.servlet.http.HttpSession;

import com.community.my.user.model.UserVO;


public class CommonUtil {
	//로그인 확인
	public static UserVO getLoginUser(HttpSession hs) {
		return (UserVO) hs.getAttribute(Const.LOGIN_USER);
	}

}
