package com.community.my;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.my.api.ApiUtils;
import com.community.my.api.model.LeagueEntryDTO;


//이거 나중에 인터셉터로 만들거
@Controller
@RequestMapping(value="/")
public class Contoller {

	@RequestMapping(value="")
	public String test(Model model) {
		//model.addAttribute("ranking",ApiUtils.getRanking());
		model.addAttribute("css", new String[] {"template", "main"});
		model.addAttribute(Const.TITLE, "메인");
		model.addAttribute(Const.VIEW,"board/main");
		return Const.MAINTEMP;
	}
	
}
