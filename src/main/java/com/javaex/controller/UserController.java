package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 로그인 폼
	@RequestMapping(value = "/lform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");

		UserVo authUser = userService.getUser(userVo);

		if (authUser != null) { // 로그인 성공 (authUser == null이 아니면)
			System.out.println("로그인 성공!");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		} else { // 로그인 실패 (authUser == null이면)
			System.out.println("로그인 실패ㅠ");
			return "redirect:/user/lform?result=fail";
		}

	}

	// 회원가입 폼
	@RequestMapping(value = "/jform", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");

		return "user/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");

		userService.join(userVo);

		return "user/joinOk";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()]");

		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	// 회원수정 폼
	@RequestMapping(value = "/mform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("[UserController.mform()]");

		// 세션에서 no 값 꺼내기
		int no = ((UserVo) session.getAttribute("authUser")).getNo();

		// 해당 no값 회원 정보 가져오기
		UserVo userVo = userService.modifyForm(no);
		model.addAttribute("userVo", userVo);

		return "user/modifyForm";
	}

	// 회원수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.modify()]");
		//세션에서 no,name들어있는 authUser 꺼내기
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		//꺼낸 authUser에서 no꺼내서 담기
		int no = authUser.getNo();
		
		//no값이 null 상태인 userVo에 setter로 no값 넣어주기
		userVo.setNo(no);
		System.out.println(userVo);
		
		//userService로 userVo 넘겨주기
		userService.modify(userVo);

		//세션에 바뀐 이름만 담아준다
		authUser.setName(userVo.getName());
		
		return "redirect:/main";
	}
}
