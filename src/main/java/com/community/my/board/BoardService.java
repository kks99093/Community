package com.community.my.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.community.my.api.ApiUtils;
import com.community.my.api.model.DetailDTO;
import com.community.my.api.model.GameDTO;
import com.community.my.api.model.LeagueEntryDTO;
import com.community.my.api.model.MatchDTO;
import com.community.my.api.model.MatchlistDTO;
import com.community.my.api.model.SummonerDTO;
import com.community.my.board.model.BoardCmtVO;
import com.community.my.board.model.BoardDMI;
import com.community.my.board.model.BoardParam;
import com.community.my.board.model.CodeVO;
import com.community.my.model.RestFile;
import com.community.my.user.model.UserParam;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	//글 등록
	public int insFreeBoard(BoardParam param) {
		return boardMapper.insFreeBoard(param);
	}
	
	//리스트 Select
	public List<BoardDMI> selFreeBoardList(BoardParam param) {
		return boardMapper.selFreeBoardList(param);
	}
	
	//카테고리 Select
	public List<CodeVO> selCategory(){
		return boardMapper.selCategory();
	}
	
	//디테일 Select
	public List<BoardCmtVO> selBoardCmt(BoardParam param) {
		return boardMapper.selBoardCmt(param);
	}
	
	//좋아요수 Select
	public BoardDMI selLikeCnt(BoardParam param) {
		BoardDMI dmi = boardMapper.selLikeCnt(param);
		if(dmi == null) {
			BoardDMI noneDMI = new BoardDMI();
			noneDMI.setCnt(0);
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
