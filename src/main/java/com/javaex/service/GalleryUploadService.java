package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryUploadService {
	
	@Autowired
	private GalleryDao galleryDao;


	public List<GalleryVo> list() {
		System.out.println("GalleryUploadService/list");
		
		List<GalleryVo> galleryList= galleryDao.selectList();
		
		return galleryList;
	}
	
	
	public String restore(MultipartFile file, String content, int user_no) {
		/////////////////데이터추출//////////////////
		
		System.out.println(file.getOriginalFilename() +", " + content);
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName" + orgName);
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName" + exName);
		
		//저장파일이름(겹치지않는이름)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName" + saveName);
		
		//파일경로
		String filePath = saveDir +"\\"+ saveName;
		System.out.println("filePath" + filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize" + fileSize);
		
		//
		
		////////////////파일 서버에 복사///////////////////////
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		////////////////////////db부분//////////////////////
		GalleryVo galleryVo = new GalleryVo(fileSize, user_no, content, filePath, orgName, saveName);
		
		galleryDao.insertGallery(galleryVo);

		return saveName;
		
	}
	
	
	public GalleryVo read(int no) {
		
		System.out.println("read");
		
		GalleryVo galleryVo = galleryDao.selectGallery(no);
		
		return galleryVo;
	}
	
	
	public int delete(int no) {
		System.out.println("delete");
		
		int cnt = galleryDao.deleteGallery(no);
		
		return cnt;
	}

}
