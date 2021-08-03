package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookDao guestDao;
	
	public List<GuestbookVo> getGuestList() {
		System.out.println("[GuestbookService.getGuestList()]");
		
		return guestDao.guestList();
	}
	
	public int guestAdd(GuestbookVo guestVo) {
		System.out.println("[GuestbookService.guestAdd()]");
		
		return guestDao.guestAdd(guestVo);
	}
	//방명록 글 저장_게시글 가져오기
	public GuestbookVo guestAdd2(GuestbookVo guestVo) {
		System.out.println("[GuestbookService.guestAdd2()]");
		
		//글 저장
		//System.out.println(guestVo);//no값이 없다
		guestDao.guestAdd2(guestVo);
		//System.out.println(guestVo);//no값이 생김!
		
		int no = guestVo.getNo(); //방금 저장한 따끈따끈한 글 번호 no에 담궈주기
		System.out.println(no);
		//글 가져오기(방금 등록한 번호)
		GuestbookVo resultVo = guestDao.selectGuestbook(no);
		System.out.println(resultVo);
		return resultVo;
	}
	
	public int guestDelete(GuestbookVo guestVo) {
		System.out.println("[GuestbookService.guestDelete()]");
		
		return guestDao.guestDelete(guestVo);
	}	

	}

