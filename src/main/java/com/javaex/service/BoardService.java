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
	
	
	public Map<String, Object> showBoardList2 (int crtPage) {
		
		System.out.println("1 service.boardList");
		//////////////////////////////////////////////////////////////
		//리스트가져오기
		/////////////////////////////////////////////////////////////
		
		
		
		//한 페이지당 글갯수
		int listCnt = 10;
		
		//현재페이지 계산 간단한건 3항연산자
		crtPage = (crtPage > 0) ? crtPage : (crtPage=1); //crtPage가 0보다 작으면 1페이지
		
		//시작글번호 startRnum 시작 rownum
		int startRnum = (crtPage-1)* listCnt; //1page -->0  +1 db에서 계산
		//endRnum 끝 rownum
		int endRnum = startRnum + listCnt; //1page --> 10 db에서 그대로 사용
		
		System.out.println("crtPage:" + crtPage);
		System.out.println("startRnum:" + startRnum);
		System.out.println("endRnum:" + endRnum);
		
		List<BoardVo> bList = boardDao.select2(startRnum, endRnum);
		
		//////////////////////////////////////////////////////////////
		//페이지 버튼 영역
		/////////////////////////////////////////////////////////////
		
		//전체글 갯수
		int totalCount = boardDao.totalCount();
		
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		//1 --> 1, 5
		//2 --> 1, 5
		//3 --> 1, 5
		//4 --> 1, 5
		//5 --> 1, 5
		//6 --> 6, 10
		//7 --> 6, 10
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount-1);
		
		boolean next = false;
		//다음 화살표 유무 next
		if(endPageBtnNo*listCnt < totalCount) {
			next = true;
		}else {
			endPageBtnNo =(int)Math.ceil(totalCount/(double)listCnt);
		}
		
		//이전 화살표 유무 prev
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("bList", bList);
		
		return pMap;
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
	
	//글쓰기
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
