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

	public Map<String, Object> list(int crtPage) { // 페이징
		System.out.println("BoardService.list(crtPage)");

		int listCnt = 10;
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);

		int startRnum = (crtPage - 1) * listCnt + 1;
		int endRnum = startRnum + listCnt - 1;
		List<BoardVo> boardList = boardDao.list(startRnum, endRnum);

		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCount: " + totalCnt);

		int pageBtnCnt = 5;

		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCnt)) * pageBtnCnt;
		int startPageBtnNo = endPageBtnNo - pageBtnCnt + 1;

		boolean next = false;
		if (endPageBtnNo * listCnt < totalCnt) {
			next = true;
		} else {
			endPageBtnNo = (int) (Math.ceil(totalCnt / (double) listCnt));
		}

		boolean prev = false;
		if (startPageBtnNo != 1) {
			prev = true;
		}

		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);

		return pMap;
	}

	public List<BoardVo> list2() { // 기존
		System.out.println("mysite4/BoardService.list2()");

		List<BoardVo> boardList = boardDao.boardList();
		return boardList;
	}

	// @Transactional
	public BoardVo getBoard(int no, String type) { // modifyForm과 합침
		System.out.println("mysite4/BoardService.getBoard()");

		if ("read".equals(type)) {
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

		/*
		 * 페이징 데이터 추가 for (int i = 1; i <= 123; i++) { boardVo.setTitle(i + "번째 글입니다.");
		 * boardVo.setContent(i + "번째 글"); boardVo.setUserNo(1);
		 * 
		 * boardDao.write(boardVo); }
		 */

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
