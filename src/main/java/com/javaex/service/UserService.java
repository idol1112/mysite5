package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	//로그인 사용자 정보 가져오기
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserService.getUser()]");
		
		return  userDao.selectUser(userVo);
	}
	
	//회원가입 파라미터 Dao로 보내기
	public void join(UserVo userVo) {
		System.out.println("[UserService.join()]");
		
		userDao.userInsert(userVo); 
	}
	
	public UserVo modifyForm(int no) {
		System.out.println("[UserService.modifyForm()]");
		
		return userDao.selectUser2(no);
		
	}
	
	public void modify(UserVo userVo) {
		System.out.println("[UserService.modifyForm()]");
		
		userDao.userUpdate(userVo);
	}
	
}
