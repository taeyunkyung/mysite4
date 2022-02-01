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
	
	public List<BoardVo> list() {
		System.out.println("mysite4/BoardService.list()");
		
		List<BoardVo> boardList = boardDao.boardList();
		return boardList;
	}
	
	// @Transactional
	public BoardVo getBoard(int no, String type) { // modifyForm과 합침
		System.out.println("mysite4/BoardService.getBoard()");
		
		if("read".equals(type)) {
			boardDao.hit(no);
			BoardVo boardVo = boardDao.read(no);						
			return boardVo;
		} else {
			BoardVo boardVo = boardDao.read(no);						
			return boardVo;
		}
	}
	
	public int write(BoardVo boardVo) {
		System.out.println("mysite4/BoardService.write()");
		
		int count = boardDao.write(boardVo);
		System.out.println("(-> BoardService)");
		
		return count;
	}
	
	public int delete(BoardVo boardVo) {
		System.out.println("mysite4/BoardService.delete()");
		
		int count = boardDao.delete(boardVo);
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
