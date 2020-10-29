package com.community.my.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.community.my.board.BoardMapper;

public class BoardInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private BoardMapper mapeer;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		 
		String[] checkKeywords = {};
		for(String keyword: checkKeywords) {
			if(uriArr[2].contains(keyword)) {
			}
		}

		return true;
	}
	
	public int loginChk(HttpServletRequest request,String str) {
		String strI_user = request.getParameter(str);
		try {
			return Integer.parseInt(strI_user);
		}catch(Exception e){
			return 0;
		}
	}
	
}
