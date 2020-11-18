package com.community.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.my.api.ApiUtils;
import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.board.BoardService;
import com.community.my.board.model.BoardParam;


//이거 나중에 인터셉터로 만들거
@Controller
@RequestMapping(value="/")
public class Contoller {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="")
	public String test(Model model) {
		LeagueEntryDTO[] rank = ApiUtils.getRanking();
		if(rank == null) {
			String err = "잠시 오류";
			model.addAttribute("err",err);
		}
		BoardParam param = new BoardParam();
		param.setI_category(0);
		model.addAttribute("allWeek", boardService.weekLikeBoard(param));
		param.setI_category(1);
		model.addAttribute("freeWeek",boardService.weekLikeBoard(param));
		param.setI_category(2);
		model.addAttribute("humorWeek",boardService.weekLikeBoard(param));
		model.addAttribute("ranking", rank);
		model.addAttribute("css", new String[] {"template", "main"});
		model.addAttribute(Const.TITLE, "VOLI.GG");
		model.addAttribute(Const.VIEW,"board/main");
		return Const.MAINTEMP;
	}
	
}
