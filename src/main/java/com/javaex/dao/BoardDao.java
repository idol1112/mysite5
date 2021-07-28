package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	//게시물 전체 가져오기
	public List<BoardVo> selectList() {
		System.out.println("[BoardDao.selectList]");
		List<BoardVo> selectList = sqlSession.selectList("board.selectList");
		System.out.println(selectList);
		return selectList;
	}
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("[BoardDao.updateHit]");
		
		return sqlSession.update("board.updateHit", no);
	}
	
	//게시판 1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("[BoardDao.selectBoard()]");
		
		return sqlSession.selectOne("board.selectBoard", no);
	}
	
	public void insertBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.insertBoard()]");
		
		sqlSession.insert("board.insertBoard", boardVo);
	}
	
	public void updateBoard(BoardVo boardVo) {
		System.out.println("[BoardDao.updateBoard()]");
		
		sqlSession.update("board.updateBoard", boardVo);
	}
	
	public void deleteBoard(int no) {
		System.out.println("[BoardDao.deleteBoard()]");
		
		sqlSession.delete("board.deleteBoard", no);
	}
}
