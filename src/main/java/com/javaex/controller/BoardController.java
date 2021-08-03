package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[BoardController.list()]");
		
		List<BoardVo> boardVo = boardService.getList();
		
		model.addAttribute("bList", boardVo);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
	public String search(Model model, @RequestParam("keyword") String keyword) {
		System.out.println("[BoardController.list()]");
		
		List<BoardVo> boardVo = boardService.getList2(keyword);
		
		model.addAttribute("bList", boardVo);
		
		
		return "board/list";
	}
	
	@RequestMapping(value = "/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.read()]");
		
		BoardVo boardVo = boardService.getBoard(no);
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	@RequestMapping(value = "/wform", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {//이거근데 그냥 url로 요청하면 로그인 안해도 들어가짐
		System.out.println("[BoardController.wform()]");
		
		return "board/writeForm";
	}
	
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.write()]");
	
		boardService.write(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/mform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.mform()]");
		//세션에 userNo값이랑 boardVo에 userNo값이랑 비교해서 예외처리 해보기
		BoardVo boardVo = boardService.modifyForm(no);
		
		model.addAttribute("boardVo", boardVo);

		return "board/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[BoardController.modify()]");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("[BoardController.delete()]");
		
		boardService.delete(no);
		
		return "redirect:/board/list";
	}

}
