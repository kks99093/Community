package com.community.my.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.community.my.Const;
import com.community.my.user.model.UserVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		//바로통과
		if(uri.equals("/")) {
			return true;
		} else if(uriArr[1].equals("res")) {
			return true;
		}
		
		HttpSession hs = request.getSession();
		UserVO login_user = (UserVO)hs.getAttribute(Const.LOGIN_USER);
		switch(uriArr[1]) {
			case "user":
				switch (uriArr[2]) {
				case "join":
					if(login_user != null) {
						response.sendRedirect("/");
						return false;
					}
				}
			case "board":
				switch(uriArr[2]) {
					case "profileImgUpd": case"boardWR":
						if(login_user == null) {
							response.sendRedirect("/");
							return false;
						}
				}
			
		}
		return true;
	}
}
