package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.BookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/api/guest")

public class ApiGuestbookController {
	
	@Autowired
	private BookService bookService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public List<GuestVo> list() {
		System.out.println("ApiguestBookController/list");
		
		List<GuestVo> guestbookList = bookService.list();
		
		System.out.println(guestbookList.toString());
		return guestbookList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="write", method=RequestMethod.POST)
	public GuestVo write(@RequestBody GuestVo guestVo) {
		System.out.println("ApiGuestbookController/write");
		System.out.println(guestVo.toString());
		
		GuestVo vo = bookService.addGuest(guestVo);
		System.out.println("보내줄vo:" + vo.toString());
		
		return vo;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public int delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("ApiGuestbookController/delete");
		//System.out.println(guestVo.toString());
		int count = bookService.delete(guestVo);
		return count;
		

		
	}
	
}
