package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guestbook") // /guestbook/addList --> /addList로 줄일 수 있음
public class GuestBookController {
	
	@Autowired
	private GuestbookService guestService;
	
	//리스트
	@RequestMapping(value="/addList")
	public String addList(Model model) {
		System.out.println("[GuestbookController.addList()]");
		
		List<GuestbookVo> guestList = guestService.getGuestList();
		model.addAttribute("gList", guestList);
		
		return "/guestbook/addList";
	}
	
	
	//등록
	@RequestMapping(value="/add")
	public String add(@ModelAttribute GuestbookVo guestVo) {
		System.out.println("[GuestbookController.add()]");
		
		guestService.guestAdd(guestVo);
		
		
		return "redirect:/guestbook/addList";
	}

	//삭제폼
	@RequestMapping(value="/deleteForm")
	public String deleteForm() {
		System.out.println("[GuestbookController.deleteForm()]");
		
		return "/guestbook/deleteForm";
	}
	
	//삭제
	@RequestMapping(value="/delete")
	public String delete(@ModelAttribute GuestbookVo guestVo) {
		System.out.println("[GuestbookController.delete()]");
		
		guestService.guestDelete(guestVo);
		
		return "redirect:/guestbook/addList";
	}
	
	@RequestMapping(value="/ajaxList")
	public String ajaxList(Model model) {
		System.out.println("[GuestbookController.ajaxList()]");
		
		//List<GuestbookVo> guestList = guestService.getGuestList();
		//model.addAttribute("gList", guestList);
		
		return "/guestbook/ajaxList";
	}
}


