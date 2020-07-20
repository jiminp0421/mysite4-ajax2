package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;



@Controller
@RequestMapping("/rboard")
public class RboardController {
	
	
	@Autowired
	private RboardService rboardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("1.rboard");
		List<RboardVo> rboardVo = rboardService.list();
		System.out.println(rboardVo.toString());
		
		model.addAttribute("bList", rboardVo);
		
		return "/rboard/list";
	}
	
	@RequestMapping("/read")
	public String read(Model model, @RequestParam ("no") int no) {
		System.out.println("2. read");
		
		rboardService.count(no);
		
		RboardVo rboardVo = rboardService.read(no);
		
		System.out.println(rboardVo.toString());
		model.addAttribute("bList", rboardVo);
		
		return "/rboard/read";
	}
	
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		System.out.println("2.writeForm");
		
		return "/rboard/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute RboardVo rboardVo, HttpSession httpSession) {
		System.out.println("3.write");
		
		UserVo vo = (UserVo)httpSession.getAttribute("authUser");
		
		rboardVo.setUser_no(vo.getNo());
		
		rboardService.write(rboardVo);
		
		return"redirect:/rboard/list";
	}
	
	@RequestMapping("/writeForm2")
	public String writeForm2() {
		System.out.println("2-1.writeForm2");
		
		return "rboard/writeForm2";
	}
	
	@RequestMapping("/write2") 
	public String write2(@ModelAttribute RboardVo rboardVo, HttpSession httpSession) {
		System.out.println("3.write");
		System.out.println(rboardVo.toString());
		UserVo vo = (UserVo)httpSession.getAttribute("authUser");
		
		rboardVo.setUser_no(vo.getNo());
		
		rboardService.write2(rboardVo);
		
		return "redirect:/rboard/list";
		
	}
	
	

	
	

}
