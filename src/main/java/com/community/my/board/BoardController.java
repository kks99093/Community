package com.community.my.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.community.my.CommonUtils;
import com.community.my.Const;
import com.community.my.api.model.GameDTO;
import com.community.my.api.model.SummonerDTO;
import com.community.my.board.model.BoardCBCVO;
import com.community.my.board.model.BoardCmtVO;
import com.community.my.board.model.BoardDMI;
import com.community.my.board.model.BoardParam;
import com.community.my.board.model.PagingVO;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	//전체
	@RequestMapping("/allBoard")
	public String allBoard(Model model, BoardParam param, PagingVO page) {
		page.setCntPerPage(10); //한페이지당 게시글
		if(param.getCurPage() == 0) {
			//초기값들
			param.setCurPage(1);
			page.setCurRange(1);
		}
		if(param.getI_category() != 0) {
			model.addAttribute("i_category",param.getI_category());
			System.out.println(param.getI_category());
		}else {
			model.addAttribute("i_category",0);
			System.out.println(param.getI_category());
		}
		
		param.setCntPerPage(page.getCntPerPage()); //리스트 서치 limit용
		String sqlText = "%"+param.getSearchText()+"%";
		param.setSqlText(sqlText);
		model.addAttribute("week",boardService.weekLikeBoard(param));
		model.addAttribute("month",boardService.monthLikeBoard(param));
		model.addAttribute("page",boardService.selPaging(page));
		model.addAttribute("data",boardService.selFreeBoardList(param));
		model.addAttribute(Const.TITLE, "자유 게시판");
		model.addAttribute(Const.VIEW,"board/all");
		model.addAttribute("css",new String[] {"all"});
		return Const.MAINTEMP;
	}
	
	//자유게시판 디테일
	@RequestMapping("/free_detail")
	public String freeDetail(Model model, BoardParam param,HttpSession hs, PagingVO page) {
		if(CommonUtils.getLoginUser(hs) != null) {
			int i_user = CommonUtils.getLoginUser(hs).getI_user();
			param.setI_user(i_user);
			model.addAttribute("likeCk",boardService.selLike(param));
		}
		//ㅡㅡㅡㅡ게시판리스트
		page.setCntPerPage(10); //한페이지당 게시글
		if(param.getCurPage() == 0) {
			//초기값들
			param.setCurPage(1);
			page.setCurRange(1);
		}
		param.setCntPerPage(page.getCntPerPage()); //리스트 서치 limit용
		String sqlText = "%"+param.getSearchText()+"%";
		param.setSqlText(sqlText);
		model.addAttribute("page",boardService.selPaging(page));
		model.addAttribute("data",boardService.selFreeBoardList(param));
		//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
		boardService.addCnt(param);
		model.addAttribute("cmt",boardService.selBoardCmt(param));
		model.addAttribute("cmtbycmt", boardService.selCmtbyCmt(param));
		model.addAttribute("content",boardService.selFreeBoardDetail(param));
		model.addAttribute(Const.TITLE, "디테일");
		model.addAttribute(Const.VIEW, "board/freeDetail");	
		model.addAttribute("css", new String[] {"boardDetail", "all"});
		return Const.MAINTEMP;
	}
	
	//좋아요수 Select ajax
	@RequestMapping("/selLikeCnt")
	@ResponseBody
	public BoardDMI selLikeCnt(BoardParam param) {
		return boardService.selLikeCnt(param);
	}
	
	//좋아요 insert Del
	@RequestMapping("/insLike")
	@ResponseBody
	public int insDelLike(BoardParam param){
		int result = 0;
		if(boardService.selLike(param) == null) {
			boardService.insLike(param);
			return 1; 
		}else {
			boardService.delLike(param);
		}
		
		return result;
	}
	
	//프로필 수정
	@RequestMapping(value = "/profileImg")
	public String profileUpdate(Model model) {
		model.addAttribute(Const.TITLE, "프로필 수정");
		model.addAttribute(Const.VIEW,"/board/profileUpdate");
		model.addAttribute("css", new String[] {"profileUpdate"});
		return Const.MAINTEMP;
	}
	
	//글쓰기 View
	@RequestMapping(value = "/boardWR", method=RequestMethod.GET)
	public String boardWR(Model model, BoardParam param) {
		if(param.getI_board() != 0) {
			model.addAttribute("content",boardService.selFreeBoardDetail(param));
		}
		model.addAttribute("category", boardService.selCategory());
		model.addAttribute(Const.TITLE, "글등록");
		model.addAttribute(Const.VIEW,"board/boardWR");
		model.addAttribute("css",new String[] {"boardWR"});
		return Const.MAINTEMP;
	}
	
	//글쓰기 등록
	@RequestMapping(value = "/boardWR", method=RequestMethod.POST)
	public String boardWR(BoardParam param) {
		boardService.insFreeBoard(param);		
		return "redirect:/board/allBoard";

	}
	
	//글삭제
	@RequestMapping(value = "/boardDel")
	public String boardDel(BoardParam param,RedirectAttributes ra) {
		boardService.delBoard(param);
		ra.addAttribute("i_category", param.getI_category());
		return "redirect:/board/allBoard";
	}
	
	//댓글 등록
	@RequestMapping(value="/insCmt")
	public String insCmt(BoardCmtVO param, RedirectAttributes ra) {
		boardService.insCmt(param);
		ra.addAttribute("i_board", param.getI_board());
		return"redirect:/board/free_detail";
	}
	
	//댓글 삭제
	@RequestMapping(value="/delCmt")
	public String delCmt(BoardParam param, RedirectAttributes ra) {
		boardService.delCmt(param);
		ra.addAttribute("i_board", param.getI_board());
		return"redirect:/board/free_detail";
	}
	
	//대댓글 등록
		@RequestMapping(value="/insCmtByCmt")
		public String insCmtByCmt(BoardCBCVO param, RedirectAttributes ra) {
			boardService.insCmtByCmt(param);
			ra.addAttribute("i_board", param.getI_board());
			return"redirect:/board/free_detail";
		}
		
	//대댓글 삭제
		@RequestMapping(value="/delCbc")
		public String delCbc(BoardParam param, RedirectAttributes ra) {
			boardService.delCbc(param);
			ra.addAttribute("i_board", param.getI_board());
			return"redirect:/board/free_detail";
		}
	
	//서치 디테일 처리 (ajax X)
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
