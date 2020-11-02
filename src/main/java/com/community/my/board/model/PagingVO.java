package com.community.my.board.model;

public class PagingVO {
	private int pagingCnt;
	private int cntPerPage;
	private int totalBoard;
	private int curPage;
	private int startIdx;
	private int endIdx;
	private int rangeCnt;
	private int curRange;
	
	public int getCurRange() {
		return curRange;
	}
	public void setCurRange(int curRange) {
		this.curRange = curRange;
	}
	public int getRangeCnt() {
		return rangeCnt;
	}
	public void setRangeCnt(int rangeCnt) {
		this.rangeCnt = rangeCnt;
	}
	public int getEndIdx() {
		return endIdx;
	}
	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalBoard() {
		return totalBoard;
	}
	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	public int getPagingCnt() {
		return pagingCnt;
	}
	public void setPagingCnt(int pagingCnt) {
		this.pagingCnt = pagingCnt;
	}
	
	
}
