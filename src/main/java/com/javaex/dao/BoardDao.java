package com.javaex.dao;

import java.util.List;

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
