package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;


@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//게시판 리스트
	
	public List<BoardVo> showBoardList (int page, String keyword) {
		
		System.out.println("1 service.boardList");
		Map<String, Object> pageMap = new HashMap<>();
		
		List<BoardVo> bList = boardDao.boardList(keyword);
		System.out.println(bList);
		return bList;
	}
	
	public int count() {
		System.out.println("1-1 service.count");

		int a = boardDao.count();
		a = (int)Math.ceil(a/5.0);
		
		return a;
	}
	
	public BoardVo read (int no) {
		System.out.println("2 service.read");
		
		
		return boardDao.read(no);
	}
	
	public int insert(BoardVo boardVo) {
		
		System.out.println("3 service.insert");
		
		return boardDao.insert(boardVo);
	}
	
	//게시판 삭제하기
	public int delete (int no) {
		System.out.println("4 service.delete");
		
		return boardDao.delete(no);
	}
	
	//게시판 수정하기
	public int modify (BoardVo boardVo) {
		System.out.println("5 service.modify");
		
		return boardDao.modify(boardVo);
	}

}
