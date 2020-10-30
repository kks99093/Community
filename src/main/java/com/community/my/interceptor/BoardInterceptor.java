package com.community.my.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.community.my.CommonUtils;
import com.community.my.board.BoardMapper;

public class BoardInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private BoardMapper boardMapeer;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		String[] checkKeywords = {"del", "Del", "upd", "Upd", "ins", "Ins"};
		for(String keyword: checkKeywords) {
			if(uriArr[2].contains(keyword)) {
				System.out.println("1확인");
				String strI_board = request.getParameter("i_board");
				int i_board = CommonUtils.parseInt(strI_board);
				if(i_board == 0) {
					return false;
				}
				String strI_user = request.getParameter("i_user");
				int i_user = Integer.parseInt(strI_user); 
				if(uriArr[2].contains("cmt") || uriArr[2].contains("Cmt")) {
					System.out.println("2확인");
					String strI_cmt = request.getParameter("i_cmt");
					int i_cmt = CommonUtils.parseInt(strI_cmt);
					boolean result = _authSuccess(i_board,i_user,i_cmt);
					return result;
				}
				//글쓴사람과 작업을하는사람이 동일인인지 확인
				boolean result = _authSuccess(i_board, i_user);
				return result;
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
	
	public boolean _authSuccess(int i_board, int i_user) { 
		return i_user == boardMapeer.selBoardChkUser(i_board);
	}
	
	public boolean _authSuccess(int i_board, int i_user, int i_cmt) { 
		return i_user == boardMapeer.selCmtChkUser(i_cmt);
	}
	
}
