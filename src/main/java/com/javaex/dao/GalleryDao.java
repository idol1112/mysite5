package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> selectList() {
		System.out.println("[GalleryDao.selectList]");
		
		return sqlSession.selectList("gallery.selectList");
	}
	
	public void insert(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.insert]");
		
		sqlSession.insert("gallery.insert", galleryVo);
		
	}
	
	public GalleryVo selectOne(int no) {
		System.out.println("[GalleryDao.selectOne]");
		System.out.println(no);
		
		return sqlSession.selectOne("gallery.selectOne", no);
	}

}
