package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;

	//ajax 리스트 가져오기
	@ResponseBody
	@RequestMapping(value="/api/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> list() {
		System.out.println("[ApiGuestbookController.list()]");
		
		List<GuestbookVo> guestbookList = guestbookService.getGuestList();
		return guestbookList;
	}
	
	//ajax 방명록 저장
	@ResponseBody
	@RequestMapping(value="/api/guestbook/write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write()]");
		GuestbookVo guestVo = guestbookService.guestAdd2(guestbookVo);
		
		return guestVo;
	}
	
}
