package com.community.my.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.community.my.Const;
import com.community.my.api.ApiUtils;
import com.community.my.api.model.DetailDTO;
import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.api.model.MatchDTO;
import com.community.my.api.model.MatchlistDTO;
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
		boardService.insFreeBoard(param);		
		return"redirect:/";

	}
	
	//닉네임 전적 검색
	@RequestMapping(value="/nameSearch")
	public String searchResult(Model model, SummonerDTO smDto) {
		smDto = ApiUtils.nameSearch(smDto);	//이름으로 정보검색
		
		LeagueEntryDTO leDTO = new LeagueEntryDTO();
		leDTO.setSummonerId(smDto.getId());	//상단에 넣을정보 받아옴
		LeagueEntryDTO[] searchInfo = ApiUtils.summonerInfo(leDTO);
		
		MatchlistDTO mtlDTO = ApiUtils.recentHistory(smDto);
		List<MatchDTO> mtDTO = ApiUtils.recentHistoryDetail(mtlDTO);

		DetailDTO dDTO = ApiUtils.detailInfo(mtDTO.get(0),mtlDTO);
		
		model.addAttribute("dDTO",dDTO);
		model.addAttribute("rankData",searchInfo[0]);
		model.addAttribute("sumData", smDto);
		model.addAttribute("css", new String[] {"searchResult"});
		model.addAttribute(Const.TITLE, "전적검색");
		model.addAttribute(Const.VIEW, "board/searchResult");
		
		return Const.MAINTEMP;
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
