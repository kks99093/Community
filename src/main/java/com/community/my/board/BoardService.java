package com.community.my.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.my.api.ApiUtils;
import com.community.my.api.model.DetailDTO;
import com.community.my.api.model.GameDTO;
import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.api.model.MatchDTO;
import com.community.my.api.model.MatchlistDTO;
import com.community.my.api.model.SummonerDTO;
import com.community.my.board.model.BoardCBCVO;
import com.community.my.board.model.BoardCmtVO;
import com.community.my.board.model.BoardDMI;
import com.community.my.board.model.BoardParam;
import com.community.my.board.model.CodeVO;
import com.community.my.board.model.PagingVO;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	//글 등록
	public int insFreeBoard(BoardParam param) {
		return boardMapper.insFreeBoard(param);
	}
	
	//글 삭제
	@Transactional
	public void delBoard(BoardParam param) {
		boardMapper.delCbc(param);
		boardMapper.delCmt(param);
		boardMapper.delLike(param);
		boardMapper.delBoard(param);
	}
	
	//리스트 Select
	public List<BoardDMI> selFreeBoardList(BoardParam param) {
		param.setCurPage((param.getCurPage()-1)*10);
		List<BoardDMI> list = boardMapper.selFreeBoardList(param);

		if(param.getSearchType() ==1 || param.getSearchType() == 3 && param.getSearchType() != 0) {
			for(BoardDMI item : list) {			
				String title = item.getTitle();
				title = title.replace(param.getSearchText()
						, "<span class=\"highlight\">"+param.getSearchText() + "</span>");
				item.setTitle(title);
			}
		}
		
		return list;
	}
	
	//일주일 인기글
	public List<BoardDMI> weekLikeBoard(BoardParam param){
		return boardMapper.weekLikeBoard(param);
	}
	//한달 인기글
	public List<BoardDMI> monthLikeBoard(BoardParam param){
		return boardMapper.monthLikeBoard(param);
	}
	
	//총페이지수 
	public PagingVO selPaging(PagingVO page) {
		page.setTotalBoard(boardMapper.selTotalPage(page).getTotalBoard());
		int cntPerPage = (page.getTotalBoard()/page.getCntPerPage())+1; //페이지수
		page.setPagingCnt(cntPerPage);
		page.setRangeCnt((cntPerPage/10) + 1);//페이징 블록수
		
		page.setStartIdx(((page.getCurRange()-1)*10)+1);//시작 인덱스 ((현재블록-1)*10)+1
		if(page.getCurRange() == page.getRangeCnt()) {
			int endIdx = cntPerPage;
			page.setEndIdx(endIdx);
		}else {
			page.setEndIdx(page.getStartIdx()+9);
		}
		return page;
	}
	
	//디테일 Select
	public BoardDMI selFreeBoardDetail(BoardParam param) {
		return boardMapper.selFreeBoardDetail(param);
	}
	
	//카테고리 Select
	public List<CodeVO> selCategory(){
		return boardMapper.selCategory();
	}
	public int addCnt(BoardParam param) {
		return boardMapper.addCnt(param);
	}
	
	//디테일 Select
	public List<BoardCmtVO> selBoardCmt(BoardParam param) {
		return boardMapper.selBoardCmt(param);
	}
	
	//댓글 등록
	public int insCmt(BoardCmtVO param) {
		return boardMapper.insCmt(param);
	}
	
	//댓글 삭제
	public int delCmt(BoardParam param) {
		boardMapper.delCbc(param);
		return boardMapper.delCmt(param);
	}
	
	//대댓글 등록
	public int insCmtByCmt(BoardCBCVO param) {
		return boardMapper.insCmtByCmt(param);
	}
	
	//대댓글 삭제
	public int delCbc(BoardParam param) {
		return boardMapper.delCbc(param);
	}
	
	//대댓글 select
	public List<BoardCBCVO> selCmtbyCmt(BoardParam param){
		return boardMapper.selCmtbyCmt(param);
	}
	
	//좋아요수 Select
	public BoardDMI selLikeCnt(BoardParam param) {
		BoardDMI dmi = boardMapper.selLikeCnt(param);
		System.out.println("0");
		if(dmi == null) {
			BoardDMI noneDMI = new BoardDMI();
			noneDMI.setLike_cnt(0);
			System.out.println("1");
			return noneDMI;
		}
		return dmi;
	}
	
	//좋아요 유무 Select
	public BoardDMI selLike(BoardParam param) {
		return boardMapper.selLike(param);
	}
	
	//좋아요 insert
	public int insLike(BoardParam param) {
		return boardMapper.insLike(param);
	}
	
	//좋아요 del
	public int delLike(BoardParam param) {
		return boardMapper.delLike(param);
	}
	
	//전적검색
	public GameDTO searchResult(SummonerDTO smDTO) {
		GameDTO gameDTO = new GameDTO();
		if(smDTO.getId() == null) {
			smDTO = ApiUtils.nameSearch(smDTO);	//이름으로 정보검색
		}else {
			smDTO = ApiUtils.idSearch(smDTO);
		}
		LeagueEntryDTO leDTO = new LeagueEntryDTO();
		leDTO.setSummonerId(smDTO.getId());	//상단에 넣을정보 받아옴
		LeagueEntryDTO[] searchInfo = ApiUtils.summonerInfo(leDTO); 
		MatchlistDTO mtlDTO = ApiUtils.recentHistory(smDTO); //전적 리스트
		
		mtlDTO.getMatches().get(0).getTimestamp();
		List<MatchDTO> mtDTO = ApiUtils.recentHistoryDetail(mtlDTO,smDTO.getMin(),smDTO.getMax()); //전적을 리스트로 상세전적 검색

		List<DetailDTO> dDTOList = new ArrayList();
		for(int i=0; i<5; i++) {
			DetailDTO dDTO = new DetailDTO();
			dDTO = ApiUtils.detailInfo(mtDTO.get(i),mtlDTO, i+smDTO.getMin());
			dDTOList.add(dDTO);
			
		}
		
		gameDTO.setdDTOList(dDTOList);
		gameDTO.setMtDTO(mtDTO);
		gameDTO.setMtlDTO(mtlDTO);
		gameDTO.setSearchInfo(searchInfo);
		gameDTO.setSmDTO(smDTO);
		
		return gameDTO;
	}
	
	
}
