package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//리스트 전체 가져오기
	public List<BoardVo> getList() {
		System.out.println("[BoardService.getList]");
		
		List<BoardVo> boardVo = boardDao.selectList();
		return boardVo;
	}
	
	//리스트 전체 가져오기(검색)
	public List<BoardVo> getList2(String keyword) {
		System.out.println("[BoardService.getList2]");
		
		
		return boardDao.selectList2(keyword);
	}
	
	//게시판 페이징 연습
	public Map<String, Object> getList3(int crtPage, String keyword) {
		System.out.println("[BoardService.getList3]");
		
		//////////////////////////////////////////////
		//리스트 가져오기
		//////////////////////////////////////////////
		int listCnt = 10;
		
		//crtPage 계산(-값일 때 1page로 처리)
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		//시작번호 계산
		int startRnum = (crtPage-1)*listCnt+1;
		
		//끝번호 계산
		int endRnum = (startRnum+listCnt)-1;
		
		List<BoardVo> boardList = boardDao.selectList3(startRnum, endRnum, keyword);
		
		//////////////////////////////////////////////
		//페이징 계산
		//////////////////////////////////////////////
		int totalCount = boardDao.selectTotal(keyword);
		System.out.println(totalCount);
		
		//페이지당 버튼 갯수
		int pageBtnCount=  5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount)) * pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		//다음 화살표 표현 유무
		boolean next = false;
		if((endPageBtnNo * listCnt) < totalCount) {
			next = true;
		}else {//다음 화살표가 없을때 endPageBtnNo 를 다시 계산해야됨.
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}						//127/10.0 -->12.7 ->> Math.ceil올림처리->>>13.0 ->>>>13
		
		//이전 화살표 표현 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		//////////////////////////////////////////////
		//Map으로 모두 차곡차곡 담아서 리턴해주기
		//////////////////////////////////////////////
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("prev", prev);
		listMap.put("startPageBtnNo", startPageBtnNo);
		listMap.put("endPageBtnNo", endPageBtnNo);
		listMap.put("next", next);
		listMap.put("boardList", boardList);
		
		
		return listMap;
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
		
		for(int i=0; i<127; i++) {
			boardVo.setTitle((i+1) + "번째 제목입니다.");
			boardVo.setContent((i+1) + "번째 내용입니다.");
			boardDao.insertBoard(boardVo);
		}
		
		
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
