package com.community.my.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.community.my.board.model.BoardCmtVO;
import com.community.my.board.model.BoardDMI;
import com.community.my.board.model.BoardParam;
import com.community.my.board.model.CodeVO;

@Mapper
public interface BoardMapper {
	int insFreeBoard(BoardParam param);
	
	List<BoardDMI> selFreeBoardList(BoardParam param);
	List<CodeVO> selCategory();
	
	List<BoardCmtVO> selBoardCmt(BoardParam param);
}
