package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//게시물 전체 가져오기(검색)
	public List<BoardVo> selectList2(String keyword) {
		System.out.println("[BoardDao.selectList2]");
		
		return sqlSession.selectList("board.selectList2", keyword);
	}
	
	//게시판 페이징 연습
	public List<BoardVo> selectList3(int startRnum, int endRnum, String keyword) {
		System.out.println("[BoardDao.selectList3]");
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("startRnum", startRnum);
		pMap.put("endRnum", endRnum);
		pMap.put("keyword" ,keyword);
		System.out.println(pMap);
		
		
		return sqlSession.selectList("board.selectList3", pMap);
	}
	
	//전체 게시물 갯수 구하기(페이징)
	public int selectTotal(String keyword) {
		System.out.println("[BoardDao.selectTotal]");
		
		return sqlSession.selectOne("board.selectTotal", keyword);
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
