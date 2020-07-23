package com.javaex.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryUploadService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryUploadService galleryUploadService;

	// 파일리스트
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GalleryController/list");
		
		List<GalleryVo> galleryList = galleryUploadService.list();
		System.out.println(galleryList.toString());
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}

	
	 //파일업로드
	 
	@RequestMapping("/upload") 
	public String upload(@RequestParam("file") MultipartFile file, Model model, @RequestParam("content") String content, HttpSession session) {
		System.out.println("GalleryController/upload");
		System.out.println(file.getOriginalFilename());
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		System.out.println(userVo.toString());
		
		int no = userVo.getNo();

		String saveName = galleryUploadService.restore(file, content, no);
		model.addAttribute("saveName",saveName);
		
	 
		return "redirect:/gallery/list"; 
	
	}
	
	@ResponseBody
	@RequestMapping("/read")
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("사진읽어오기");
		
		System.out.println(no);
		
		GalleryVo galleryVo = galleryUploadService.read(no); // service return galleryVo
		
		return galleryVo;
	}
	
	//파일삭제
	@ResponseBody
	@RequestMapping("/delete") 
	public int delete(@RequestParam ("no") int no) {
		 System.out.println("GalleryController/delete");
		 
		 int cnt = galleryUploadService.delete(no);
		 
		 return cnt;
	 }
	 

	 

}
