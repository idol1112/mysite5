package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	//로그인 사용자 정보 가져오기(no,name)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		
		return sqlSession.selectOne("user.selectUser", userVo);

	}
	
	//로그인 사용자 정보 가져오기2(id,pw,name,gender)
	public UserVo selectUser2(int no) {
		System.out.println("[UserDao.selectUser2()]");
		
		return sqlSession.selectOne("user.selectUser2", no);
		
	}
	
	//회원 정보 입력
	public void userInsert(UserVo userVo) {
		System.out.println("[UserDao.userInsert()]");
		
		int count = sqlSession.insert("user.userInsert", userVo);
		
		System.out.println(count + "건 저장되었습니다.");
		
	}
	
	//회원 정보 수정
	public void userUpdate(UserVo userVo) {
		System.out.println("[UserDao.userUpdate()]");
		
		int count = sqlSession.update("user.userUpdate", userVo);
		
		System.out.println(count + "건 수정되었습니다.");
	}
}
