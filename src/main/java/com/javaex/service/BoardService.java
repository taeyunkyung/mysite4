package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	// private UserDao userDao;
	
	public List<BoardVo> list() {
		System.out.println("mysite4/BoardService.list()");
		
		List<BoardVo> boardList = boardDao.boardList();
		return boardList;
	}
	
	public BoardVo read(int no) {
		System.out.println("mysite4/BoardService.read()");
		
		BoardVo boardVo = boardDao.read(no);
		boardDao.hit(no);
		
		return boardVo;
	}
	
	public int write(BoardVo boardVo) {
		System.out.println("mysite4/BoardService.write()");
		
		int count = boardDao.write(boardVo);
		System.out.println("(-> BoardService)");
		
		return count;
	}
	
	public int delete(int no) {
		System.out.println("mysite4/BoardService.delete()");
		
		int count = boardDao.delete(no);
		System.out.println("(-> BoardService)");
		
		return count;
	}
	
	public int modify(BoardVo boardVo) {
		System.out.println("mysite4/BoardService.modify()");
		
		int count = boardDao.update(boardVo);
		System.out.println("(-> BoardService)");
		
		return count;
	}
}
