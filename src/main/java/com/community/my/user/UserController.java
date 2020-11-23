package com.community.my.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.community.my.Const;
import com.community.my.model.RestFile;
import com.community.my.user.model.UserDMI;
import com.community.my.user.model.UserParam;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, UserParam param, RedirectAttributes ra, HttpSession hs, HttpServletRequest request) {
		int result = userService.login(param, hs);
		String msg = null;
		String lastPage = "/";
		switch (result) {
		case Const.NO_ID:
			msg= "아이디가 없습니다";
			break;
		case Const.NO_PW:
			msg = "비밀번호가 틀립니다";
			break;
		case Const.SUCCESS:
			lastPage = request.getHeader("Referer");
			break;
		}

		ra.addFlashAttribute("msg",msg);
		return "redirect:"+lastPage;
	}
	
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpSession hs, HttpServletRequest request) {
		String lastPage = request.getHeader("Referer");
		hs.invalidate();
		return "redirect:"+lastPage;
	}
	
	//프로필 업데이트
	@RequestMapping(value = "/updProfile")
	public String changeProfile(RestFile param, HttpServletRequest hsr, HttpSession hs){
		String lastPage = hsr.getHeader("Referer");
		userService.updProfile(param, hsr, hs);
		return "redirect:"+lastPage;
	}
	
	
	//가입 View
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) {
		model.addAttribute("css", new String[] {"template", "join"});
		model.addAttribute(Const.TITLE, "회원가입");
		model.addAttribute(Const.VIEW, "user/join");
		return Const.MAINTEMP ;
	}
	
	//가입 DB
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserParam param) {
		userService.insJoin(param);
		return "redirect:/" ;
	}
	
	//인증메일 보내기
	@RequestMapping("/sendEmail" )
	@ResponseBody
	public String sendEmail(@RequestBody UserParam param){
		String authKey = userService.sendAuthMail(param.getUser_email());
		return authKey;
	}
	
	//아이디 중복체크
	@RequestMapping("/overlapChk")
	@ResponseBody
	public int overlapChk(@RequestBody UserParam param,HttpSession hs,HttpServletResponse response) {
		return userService.login(param, hs);
	}

}
