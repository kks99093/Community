package com.community.my.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.community.my.Const;
import com.community.my.api.model.GameDTO;
import com.community.my.api.model.SummonerDTO;
import com.community.my.board.model.BoardParam;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//자유게시판
	@RequestMapping("/free")
	public String freeBoard(Model model, BoardParam param) {
		model.addAttribute("data",boardService.selFreeBoardList(param));
		model.addAttribute(Const.TITLE, "자유 게시판");
		model.addAttribute(Const.VIEW,"board/free");
		model.addAttribute("css",new String[] {"free"});
		return Const.MAINTEMP;
	}
	
	//자유게시판 디테일
	@RequestMapping("/free_detail")
	public String freeDetail(Model model, BoardParam param) {
		model.addAttribute("cmt",boardService.selBoardCmt(param));
		model.addAttribute("content",boardService.selFreeBoardList(param));
		model.addAttribute(Const.TITLE, "디테일");
		model.addAttribute(Const.VIEW, "board/freeDetail");	
		model.addAttribute("css", new String[] {"boardDetail"});
		return Const.MAINTEMP;
	}
	
	//글쓰기 View
	@RequestMapping(value = "/boardWR", method=RequestMethod.GET)
	public String boardWR(Model model) {
		model.addAttribute("category", boardService.selCategory());
		model.addAttribute(Const.TITLE, "글등록");
		model.addAttribute(Const.VIEW,"board/boardWR");
		model.addAttribute("css",new String[] {"boardWR"});
		return Const.MAINTEMP;
	}
	
	//글쓰기 DB등록
	@RequestMapping(value = "/boardWR", method=RequestMethod.POST)
	public String boardWR(BoardParam param) {
		param.setNick_nm("ddd");
		boardService.insFreeBoard(param);		
		return"redirect:/";

	}
	
	//디테일 처리 (ajax X)
	@RequestMapping(value="/nameSearch")
	public String searchResult(Model model, SummonerDTO smDTO) {
		smDTO.setMin(0); //시작값 설정
		smDTO.setMax(5); //시작값 설정
		GameDTO gameDTO = boardService.searchResult(smDTO);
		
		model.addAttribute("game",gameDTO);
		model.addAttribute("css", new String[] {"searchResult"});
		model.addAttribute(Const.TITLE, "전적검색");
		model.addAttribute(Const.VIEW, "board/searchResult");
		
		return Const.MAINTEMP;
	}
	
	//게임목록 받아오기 (ajax)
	@RequestMapping(value="/moreGame")
	@ResponseBody
	public GameDTO moreGame(GameDTO gameDTO, SummonerDTO smDTO){
		smDTO.setId(gameDTO.getSumId());
		smDTO.setMin(gameDTO.getMin());
		smDTO.setMax(gameDTO.getMax());
		GameDTO result = boardService.searchResult(smDTO);
		return result;
	}
	
	
	/* 나중에 하자
	// 스크립트 필터
	@SuppressWarnings("unused")
	private String scriptFilter(final String content) {
		String[] filters = {"<",">"};
		//모든 태그를 막으려면 <랑 >를 &lt; &gt;로 바꿔주면 되나?
		String[] fiterReplaces = {"&lt;", "&gt;"};
		
		String result = content;
		for(int i=0; i<filters.length; i++) {
			result = result.replace(filters[i], fiterReplaces[i]);
		//replace : 바꾸다
		//ctnt의 문자열중 filter[i]부분을 fiterReplaces[i]로 바꾼다
		}
		return result;
	
	
	}
	*/
	
	

}
