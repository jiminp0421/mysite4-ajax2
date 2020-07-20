package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/guest")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		System.out.println("/guest/list");
		
		List<GuestVo> gList = bookService.list();
		model.addAttribute("gList", gList);
		
		return "guestbook/addList";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("/guest/write");
		bookService.write(guestVo);
		System.out.println(guestVo.toString());
		
		return "redirect:/guest/list";
	}
	
	@RequestMapping("/deleteForm")
	public String deleteForm(Model model, @RequestParam("fno") int no) {
		
		System.out.println("/guest/deleteForm");
		
		model.addAttribute("dNo", no);
		
		return "guestbook/deleteForm";
		
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestVo guestVo) {
		
		System.out.println("/delete");
		bookService.delete(guestVo);
		
		return "redirect:/guest/list";
	}
	
	
	//ajax 방명록
	@RequestMapping("/ajaxlist")
	public String ajaxList() {
		System.out.println("guestbookController/ajaxList");
		
		return"guestbook/ajaxList";
	}

}
