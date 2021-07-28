package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList]");
		
		List<BoardVo> boardVo = boardDao.selectList();
		return boardVo;
	}
	
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard]");
		
		//조회수 올리기
		int count = boardDao.updateHit(no);
		
		//게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);
		
		return boardVo;
		
	}
	
	public void write(BoardVo boardVo) {
		System.out.println("[BoardService.write]");
		
		boardDao.insertBoard(boardVo);
	}
	
	public BoardVo modifyForm(int no) {
		System.out.println("[BoardService.modifyForm]");
		
		BoardVo boardVo = boardDao.selectBoard(no);
		
		return boardVo;
	}
	
	public void modify(BoardVo boardVo) {
		System.out.println("[BoardService.modify]");
		
		boardDao.updateBoard(boardVo);
	}
	
	public void delete(int no) {
		System.out.println("[BoardService.delete]");
		
		boardDao.deleteBoard(no);
	}
	
}
