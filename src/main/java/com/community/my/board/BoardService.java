package com.community.my.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.my.board.model.BoardCmtVO;
import com.community.my.board.model.BoardDMI;
import com.community.my.board.model.BoardParam;
import com.community.my.board.model.CodeVO;

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
}
