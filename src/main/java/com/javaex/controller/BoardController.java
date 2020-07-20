package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	@RequestMapping("/boardList")
	public String boardList(Model model, @RequestParam ("page") int page) {
		
		int count = boardService.count();
		System.out.println("C.boardlist");
		
		List<BoardVo> bList = boardService.showBoardList(page, "");
		
		
//		Map<String, Integer> countMap= boardService.count("");
		
		model.addAttribute("bList", bList);
		model.addAttribute("count", count);
		
		
		return "/board/list";
	}
	
	
	
	//게시판 리스트 페이징 추가하기전
	/*@RequestMapping("/boardList")
	public String boardList(Model model) {
		
		System.out.println("C.boardlist");
		
		List<BoardVo> bList = boardService.showBoardList("");
		
		model.addAttribute("bList", bList);
		
		return "/board/list";
	}*/
	
	//게시판 읽어오기
	
	@RequestMapping("/boardRead")
	public String boardRead(Model model, @RequestParam ("no") int no) {
		
		System.out.println("C.boardRead");
		
		
		model.addAttribute("rNo", boardService.read(no));
		System.out.println(model.toString());
		System.out.println(no);
		
		return "/board/read";
	}
	
	//게시판 글쓰기폼
	@RequestMapping("/writeForm")
	public String writeForm() {
	
		return "/board/writeForm";
	}
	
	//게시판 글쓰기
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo boardVo) {
		
		System.out.println("C.boardWrite");
		

		boardService.insert(boardVo);
		System.out.println(boardVo);
		return "redirect:/board/boardList";
	}
	
	
	//게시판 삭제하기
	@RequestMapping("/delete")
	public String delete(@RequestParam("user_no") int no) {
		
		System.out.println("C.delete");
		System.out.println(no);
		
		boardService.delete(no);
		
		return "redirect:/board/boardList";
	}
	
	
	//게시판 수정하기
	@RequestMapping("/modifyForm")
	public String modifyForm (Model model, @RequestParam ("no") int no) {
		
		model.addAttribute("rNo", boardService.read(no));
		System.out.println("C.modifyForm");
		
		return "/board/modifyForm";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		
		System.out.println("C.modify");
		
		boardService.modify(boardVo);
		
		return "redirect:/board/boardList";
	}

	

	//게시판 찾기 (paging 추가하기전)
	/*@RequestMapping("/search")
	public String search(Model model, @RequestParam ("keyword") String keyword) {
		System.out.println("C.search");
		List<BoardVo> bList = boardService.showBoardList(keyword);
		
		model.addAttribute("bList", bList);
		return "/board/list";
	}*/
//	
//	@RequestMapping("/search")
//	public String search(Model model, @RequestParam("page") int page, @RequestParam ("keyword") String keyword) {
//		System.out.println("C.search");
//		
//		List<BoardVo> bList = boardService.showBoardList(keyword);
//		Map<String, Integer> countMap= boardService.count(keyword);
//		
//		model.addAttribute("bList", bList);
//		return "/board/list";
//	}


}
