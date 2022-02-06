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
	
	public List<GalleryVo> list() {
		System.out.println("GalleryDao.list()");
		return sqlSession.selectList("gallery.selectList");
	}
	
	public int save(GalleryVo galleryVo) {
		System.out.println("GallerDao.save()");
		return sqlSession.insert("gallery.fileSave", galleryVo);
	}
	
	public GalleryVo view(int no) {
		System.out.println("GalleryDao.view()");
		return sqlSession.selectOne("gallery.viewOne", no);
	}
	
	public int delete(GalleryVo galleryVo) {
		System.out.println("GalleryDao.delete()");
		return sqlSession.delete("gallery.delete", galleryVo);
	}

}
