package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/api/user")
public class ApiUserController {
	@Autowired
	private UserService userService;
	
	//아이디체크(ajax)
	@ResponseBody
	@RequestMapping(value="/idCheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("[ApiUserController.idCheck()]");
		System.out.println(id);
		
		return userService.getUser(id);
	}
	
	//json방식으로 데이터 받기
	@ResponseBody
	@RequestMapping(value="/join", method = {RequestMethod.GET, RequestMethod.POST})
	public int join(@RequestBody UserVo userVo) {
		System.out.println("[ApiUserController.join()]");
		
		System.out.println(userVo);
		
		int count = userService.join(userVo);
		return count;
	}

}
