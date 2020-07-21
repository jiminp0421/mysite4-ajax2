package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//보드 리스트
	
	public List<BoardVo> boardList(String keyword) {
		
		
		List<BoardVo> bList = sqlSession.selectList("board.boardList", keyword);
		System.out.println("BoardDaoList");
		System.out.println(bList.toString());
		
		return bList;
	}
	
	
	//페이징 리스트
	public List<BoardVo> select2(int startRnum, int endRnum) {
		System.out.println("boardDao/select2");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		return sqlSession.selectList("board.boardList2", map);
	}
	
	
	//토탈카운트
	public int totalCount() {
		System.out.println("boardDao/totalCount");
		
		return sqlSession.selectOne("board.totalCount");
	}
	
	
	
	public int count() {
		System.out.println("BoardDaoCount");
		
		return sqlSession.selectOne("board.count");
	}
	
	
	public BoardVo read(int no) {
		System.out.println("BoardDaoRead");
		
		
		return sqlSession.selectOne("board.read", no) ;
	}
	
	public int insert(BoardVo boardVo) {
		System.out.println("BoardDaoInsert");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	public int delete(int no) {
		System.out.println("Daodelete");
		System.out.println(no);
		return sqlSession.delete("board.delete", no);
	}
	
	public int modify(BoardVo boardVo) {
		System.out.println("BoardDaoModify");
		
		System.out.println(boardVo);
		
		return sqlSession.update("board.update", boardVo);
	}
	
	

}
