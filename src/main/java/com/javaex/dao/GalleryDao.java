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
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.selectGalleryList");
		
		return galleryList;
	}
	
	
	public int insertGallery(GalleryVo galleryVo) {
		System.out.println("Gallery.insert");
		
		sqlSession.insert("gallery.insertGallery", galleryVo);
		
		return 1;
	}
	
	
	public GalleryVo selectGallery(int no) {
		System.out.println("Gallery.read");
		
		GalleryVo galleryVo = sqlSession.selectOne("gallery.selectGallery", no);
		System.out.println(galleryVo);
		
		return galleryVo;
	}
	
	
	
	public int deleteGallery(int no) {
		System.out.println("Gallery.delete");
		
		int cnt = sqlSession.delete("gallery.deleteGallery", no);
		
		return cnt;
	}
	
	
	
	

}
