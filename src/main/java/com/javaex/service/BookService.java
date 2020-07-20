package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BookDao;
import com.javaex.vo.GuestVo;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	//방명록 리스트, ajax list
	public List<GuestVo> list() {
		System.out.println("bookService.list");
		
		List<GuestVo> gList = bookDao.getGuestBookList();
		
		System.out.println(gList.toString());
		
		
		return gList;
	}

	
	public int write(GuestVo guestVo) {
		System.out.println("bookService.insert");
		
		int count = bookDao.guestBookinsert(guestVo);
		
		return count;
	}
	
	public int delete(GuestVo guestVo) {
		System.out.println("service/delete");
		
		int count = bookDao.guestBookdelete(guestVo);
		
		return count;
	}
	
	//방명록 글 저장(ajax)
	public GuestVo addGuest(GuestVo guestVo) {
		//wjwkd
		bookDao.insert(guestVo);
		int no = guestVo.getNo(); //연구해볼것
		System.out.println("no값" +no);
		
		System.out.println(no);
		
		//저장된 데이터 가져오기
		return bookDao.selectByNo(no);		
	}
	
	
	

}
