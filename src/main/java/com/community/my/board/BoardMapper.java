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
	int insLike(BoardParam param);
	int delLike(BoardParam param);
	int insCmt(BoardCmtVO param);
	int selBoardChkUser(int i_board);
	int selCmtChkUser(int i_cmt);
	
	BoardDMI selLike(BoardParam param);
	BoardDMI selLikeCnt(BoardParam param);
	
	List<BoardDMI> selFreeBoardList(BoardParam param);
	BoardDMI selFreeBoardDetail(BoardParam param);
	List<CodeVO> selCategory();
	
	List<BoardCmtVO> selBoardCmt(BoardParam param);
}
